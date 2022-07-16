package hospitalobjects;

import inputservices.UserOutputService;

import java.util.*;

public class Hospital {
    private String name;
    private HashMap<String, List<Doctor>> specialtyDirectory;

    public Hospital(String nameInput) {
        name = nameInput;
        specialtyDirectory = new HashMap<>();
    }

    public Hospital() {
    }

    public HashMap<String, List<Doctor>> getSpecialtyDirectory() {
        return specialtyDirectory;
    }

    public String getName() {
        return name;
    }

    public void addDoctor(Doctor doctor, UserOutputService userOutputService) {
        // put doctor specialty title in a string variable for readability
        String doctorSpecialty = doctor.getSpecialty().getTitle();

        if (specialtyDirectory.containsKey(doctorSpecialty)) {
            List<Doctor> withSpecialty = specialtyDirectory.get(doctorSpecialty);
            withSpecialty.add(doctor);
        } else {
            List<Doctor> doctors = new ArrayList<>();
            doctors.add(doctor);
            specialtyDirectory.put(doctorSpecialty, doctors);
        }
    }

    public void addPatient(Patient patient, UserOutputService userOutputService) {
        // Query Hospital Directory, for a List of Doctors for respective specialty
        List<Doctor> doctorMatchList = specialtyDirectory.get(patient.getPatientAilment().getAssociatedSpecialty());

        // Reject patient if no Doctor for their needs exist
        if (doctorMatchList == null) {
            userOutputService.printMessage("Unable to service this patient... No doctor with appropriate specialty available");
            return;
        }

        // Choose a Doctor that is available, and match with patient
        Doctor matchedDoctor = findDoctorWithShortestListOfPatients(doctorMatchList);
        matchedDoctor.addPatient(patient);
    }

    private Doctor findDoctorWithShortestListOfPatients(List<Doctor> doctors) {
        Doctor chosenDoctor = null;
        for (Doctor doctor : doctors) {
            if (chosenDoctor == null) {
                chosenDoctor = doctor;
            } else if (chosenDoctor.getPatientList().size() > doctor.getPatientList().size()) {
                chosenDoctor = doctor;
            }
        }
        return chosenDoctor;
    }

    public void printOutHospitalWorld() {
        System.out.println("\n\n%%%%%%%%%%%%%%%%% Hospital " + getName() + " %%%%%%%%%%%%%%%%%\n");

        System.out.println("### Hospital Specialty Departments and Staff and respective Patients ###");
        for (Map.Entry<String, List<Doctor>> specialtyDepartment : getSpecialtyDirectory().entrySet()) {

            System.out.println("  **** " + specialtyDepartment.getKey() + " ****");

            List<Doctor> departmentDoctors = specialtyDepartment.getValue();
            for (Doctor doctor : departmentDoctors) {
                System.out.println("    ===> " + doctor.getName());
                printPatientList(doctor.getPatientList());
            }

        }
    }

    public void printPatientList(List<Patient> patientList) {
        if (patientList.isEmpty()) System.out.println("           + Doctor has no patients");

        for (Patient patient : patientList) {
            System.out.println("           + " + patient.getName() + " = has = " + patient.getPatientAilment().getName() + " health: " + patient.getPatientHealthIndex());
        }
    }
}
