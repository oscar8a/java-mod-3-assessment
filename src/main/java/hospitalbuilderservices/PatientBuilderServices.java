package hospitalbuilderservices;

import hospitalobjects.Ailment;
import hospitalobjects.AilmentEnum;
import hospitalobjects.Patient;
import inputservices.*;

import java.util.Arrays;

public class PatientBuilderServices {
    private UserInputService userInputService;
    private UserOutputService userOutputService;

    public PatientBuilderServices(UserInputService userInputService, UserOutputService userOutputService) {
        this.userInputService = userInputService;
        this.userOutputService = userOutputService;
    }


    public Patient createPatient() {
        String name = userInputService.getUserStringInput("What's the patient's name?");
        Ailment patientAilment = createAilment();

        return new Patient(name, patientAilment);
    }

    public Ailment createAilment() {
        userOutputService.printMessage("[1.Cold]  [2.Skin Rash]  [3.Broken Bone]  [4.Something Else]");
        int ailmentOption = userInputService.getUserIntInput("What's the patient's symptom? (Choose from options)");

        switch (ailmentOption) {
            case 1:
                return new Ailment("Cold", "Pediatrics", 70, true);
            case 2:
                return new Ailment("Skin Rash", "Dermatology", 70, true);
            case 3:
                return new Ailment("Broken Bone", "Orthopedics", 70, true);
            case 4:
            default:
                return new Ailment("unknown", "Internal Medicine", 10, false);
        }
    }
}
