package hospitalobjects;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    String name;
    String specialty;
    int healingIndex;
    List<Patient> patientList;

    public Doctor(String inputName, String inputSpecialty, int inputHealingIndex) {
        name = inputName;
        specialty = inputSpecialty;
        healingIndex = inputHealingIndex;
        patientList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getHealingIndex() {
        return healingIndex;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}
