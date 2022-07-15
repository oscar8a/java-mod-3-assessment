package hospitalobjects;

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
}
