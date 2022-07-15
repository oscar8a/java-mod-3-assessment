package hospitalobjects;

import java.util.*;

public class Hospital {
    private final String name;
    private HashMap<String, List<Doctor>> specialtyDirectory;

    public Hospital(String nameInput) {
        name = nameInput;
        specialtyDirectory = new HashMap<String, List<Doctor>>();
    }

    public HashMap<String, List<Doctor>> getSpecialtyDirectory() {
        return specialtyDirectory;
    }

    public String getHospitalName() {
        return name;
    }

    public void addDoctor(Doctor doctor) {
        if (specialtyDirectory.containsKey(doctor.getSpecialty())) {
            List<Doctor> withSpecialty = specialtyDirectory.get(doctor.getSpecialty());
            withSpecialty.add(doctor);
        } else {
            List<Doctor> doctors = new ArrayList<>();
            doctors.add(doctor);
            specialtyDirectory.put(doctor.getSpecialty(), doctors);
        }
    }

    public void addPatient(Patient patient) {
        // Query Hospital Directory, for a List of Doctors for respective specialty
        List<Doctor> doctorMatchList = specialtyDirectory.get(patient.getMyDisease().getName());

        // Choose a Doctor
        Doctor matchedDoctor = findDoctorWithShortestListOfPatients(doctorMatchList);
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
        System.out.println("\n\n%%%%%%%%%%%%%%%%% Hospital " + getHospitalName() + " %%%%%%%%%%%%%%%%%\n");

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
            System.out.println("           + " + patient.getName() + " " + patient.getMyDisease().getName());
        }
    }
}
