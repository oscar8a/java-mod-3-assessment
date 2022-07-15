package hospitalbuilderservices;

import hospitalobjects.*;
import inputservices.*;

public class HospitalBuilderServices {
    private UserInputService userInputService;
    private UserOutputService userOutputService;
    private DoctorBuilderServices doctorBuilderService;
    private PatientBuilderServices patientBuilderService;
    private static final int NUMBER_OF_DOCTORS = 3;
    private static final int NUMBER_OF_PATIENTS = 5;

    public HospitalBuilderServices(UserInputService userInputService, DoctorBuilderServices doctorBuilderService,
                                  PatientBuilderServices patientBuilderService, UserOutputService userOutputService) {
        this.userInputService = userInputService;
        this.doctorBuilderService = doctorBuilderService;
        this.patientBuilderService = patientBuilderService;
        this.userOutputService = userOutputService;
    }

    public Hospital createHospital() {
        String name = userInputService.getUserStringInput("Please enter a name for your Hospital");
        Hospital hospital = new Hospital(name);

        for (int i = 0; i < NUMBER_OF_DOCTORS; i++) {
            Doctor doctor = doctorBuilderService.createDoctor();
            hospital.addDoctor(doctor, userOutputService);
        }
        for (int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            Patient patient = patientBuilderService.createPatient();
            hospital.addPatient(patient, userOutputService);
        }
        return hospital;
    }

    // Create a method that builds hospital with all the doctors and patients
    // write code to assign all doctors to their respective hospital department
    // assign patients to their respective doctors
    // once done, call the function that will print the Hospital World, create all respective getters in order to print
}
