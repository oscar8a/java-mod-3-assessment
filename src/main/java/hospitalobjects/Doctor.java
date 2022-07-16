package hospitalobjects;

import inputservices.UserOutputService;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    String name;
    Specialty specialty;
    int healingIndex;
    List<Patient> patientList;

    public Doctor(String inputName, Specialty inputSpecialty, int inputHealingIndex) {
        name = inputName;
        specialty = inputSpecialty;
        healingIndex = inputHealingIndex;
        patientList = new ArrayList<>();
    }

    public Doctor() {
    }

    public String getName() {
        return name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public int getHealingIndex() {
        return healingIndex;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public double calculateTreatmentsUntilCured(Patient patient) {
        int patientHealth = patient.getPatientHealthIndex();
        int difference = 100 - patientHealth;

        return (double) difference / healingIndex;
    }

    public boolean isPatientCurable(Patient patient) {
        return calculateTreatmentsUntilCured(patient) < specialty.getNumberOfTreatmentsToCure();
    }

    public void treatPatient(Patient patient, UserOutputService userOutputService) {
        int counter = 1;
        if (!isPatientCurable(patient)){

            while (!isPatientCurable(patient)) {
                userOutputService.printMessage("Applying treatment " + counter + " ==> " + patient.getName() + " health at " + patient.getPatientHealthIndex());
                userOutputService.printMessage("...you won't recover =(");
                counter++;
                if (counter == 15) break;
            }
        } else {
            int treatmentsUntilCured = (int) Math.round(calculateTreatmentsUntilCured(patient));

            userOutputService.printMessage(patient.getName() + " health at " + patient.getPatientHealthIndex() + " || Curable? = " + isPatientCurable(patient) + " || Treatments to cure " + treatmentsUntilCured);
            userOutputService.printMessage("Doctor " + getName() + ", specialist in " + getSpecialty().getTitle() +", is treating " + patient.getName() + " for " + patient.getPatientAilment().getName());

            for (int i = 0; i < treatmentsUntilCured; i++){
                patient.setPatientHealthIndex(patient.getPatientHealthIndex() + getHealingIndex());
                userOutputService.printMessage("Applying treatment " + counter + " ==> " + patient.getName() + " health at " + patient.getPatientHealthIndex());
                counter++;
                if (patient.getPatientHealthIndex() >= 100){
                    userOutputService.printMessage("you have been cured");
                    removePatientFromDoctorList(patient);
                    break;
                }
            }
        }
    }

    public void printDoctorPatientList() {
        int counter = 1;
        for (int i = 0; i < getPatientList().size(); i++) {
            System.out.println(counter + " " + patientList.get(i).getName());
            counter++;
        }
    }

    public Patient getSinglePatient(int idx) {
        return getPatientList().get(idx-1);
    }

    public void removePatientFromDoctorList(Patient patient) {
        getPatientList().remove(patient);
    }
}
