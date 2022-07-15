package hospitalobjects;

import java.util.HashMap;
import java.util.List;

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
}
