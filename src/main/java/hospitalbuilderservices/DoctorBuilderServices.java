package hospitalbuilderservices;

import hospitalobjects.Ailment;
import hospitalobjects.Doctor;
import hospitalobjects.Specialty;
import inputservices.*;

public class DoctorBuilderServices {
    private UserInputService userInputService;
    private UserOutputService userOutputService;

    public DoctorBuilderServices(UserInputService userInputService, UserOutputService userOutputService) {
        this.userInputService = userInputService;
        this.userOutputService = userOutputService;
    }

    public Doctor createDoctor() {
        String name = userInputService.getUserStringInput("What's the doc's name?");
        userOutputService.printMessage("[1.Pediatrics]  [2.Dermatology]  [3.Orthopedics]  [4.Internal Medicine]");
        int specialtyOptionInput = userInputService.getUserIntInput("What's the doc's specialty?");
        Specialty specialty = new Specialty();
        switch (specialtyOptionInput) {
            case 1:
                specialty.setTitle("Pediatrics");
                specialty.setNumberOfTreatmentsToCure(4);
                break;
            case 2:
                specialty.setTitle("Dermatology");
                specialty.setNumberOfTreatmentsToCure(5);
                break;
            case 3:
                specialty.setTitle("Orthopedics");
                specialty.setNumberOfTreatmentsToCure(2);
                break;
            default:
                specialty.setTitle("Internal Medicine");
                specialty.setNumberOfTreatmentsToCure(3);
        }
        return new Doctor(name, specialty, 10);
    }
}
