package hospitalobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
}
