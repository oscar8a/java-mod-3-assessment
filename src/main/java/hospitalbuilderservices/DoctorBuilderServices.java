package hospitalbuilderservices;

import hospitalobjects.Doctor;
import inputservices.UserInputService;

public class DoctorBuilderServices {
    private UserInputService userInputService;

    public DoctorBuilderServices(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public Doctor createDoctor() {
        String name = userInputService.getUserStringInput("What's the doc's name?");
        String specialty = userInputService.getUserStringInput("What's the doc's specialty?");
        // TODO: Modify healing index depending on specialty, or user input?
        Doctor doctor = new Doctor(name, specialty, 10);
        return doctor;
    }
}
