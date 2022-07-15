package hospitalbuilderservices;

import inputservices.UserInputService;

public class HospitalBuilderServices {
    private UserInputService userInputService;
    private DoctorBuilderServices doctorBuilderService;
    private PatientBuilderServices patientBuilderService;
    private static final int NUMBER_OF_DOCTORS = 3;
    private static final int NUMBER_OF_PATIENTS = 5;

    public HospitalBuilderServices(UserInputService userInputService, DoctorBuilderServices doctorBuilderService,
                                  PatientBuilderServices patientBuilderService) {
        this.userInputService = userInputService;
        this.doctorBuilderService = doctorBuilderService;
        this.patientBuilderService = patientBuilderService;
    }

    // Create a method that builds hospital with all the doctors and patients
    // write code to assign all doctors to their respective hospital department
    // assign patients to their respective doctors
    // once done, call the function that will print the Hospital World, create all respective getters in order to print
}
