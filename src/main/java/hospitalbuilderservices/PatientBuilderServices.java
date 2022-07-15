package hospitalbuilderservices;

import hospitalobjects.Patient;
import inputservices.UserInputService;
public class PatientBuilderServices {
    private UserInputService userInputService;

    public PatientBuilderServices(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public Patient createPatient() {
        String name = userInputService.getUserStringInput("What's the patient's name?");
        String specialty = userInputService.getUserStringInput("What's the patient's specialty?");
        Patient patient = new Patient(name);
        return patient;
    }
}
