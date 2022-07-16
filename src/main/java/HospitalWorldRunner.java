import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import hospitalobjects.Doctor;
import hospitalobjects.Patient;
import inputservices.*;
import hospitalbuilderservices.*;
import hospitalobjects.Hospital;


public class HospitalWorldRunner {
    private static String hospitalJSONFileName = "";

    public static void main(String[] args) throws IOException {

        // Check for Invalid args
        if (args.length >= 1) {
            readAndWriting(args);
        } else {
            System.out.println("invalid arguments");
        }

        // Instantiate Output Services and pass into the Try Catch block, where it has input services
        UserOutputService userOutputService = new HospitalOutputService();
        try (UserInputService userInputService = new HospitalInputService(userOutputService)) {
            boolean keepProgramRunning = true;

            userOutputService.printMessage("Starting HospitalWorldRunner...");

            // Prompt user to restore from JSON file or not
            boolean restore = promptUserStartPoint(userInputService, userOutputService);
            if (restore) {
                // Restore using ObjectMapper from jackson.databind.ObjectMapper
                Hospital restoredHospital = new ObjectMapper().readValue(new File(hospitalJSONFileName), Hospital.class);

                // Print Out Hospital World
                restoredHospital.printOutHospitalWorld();

                // Treatment of Patients functionality ** Broken
//                treatPatients(restoredHospital, userInputService, userOutputService);

                // JSON Print to Console & Save to File
                writeJson(restoredHospital);
            } else {
                // Instantiate Hospital Builder Services
                DoctorBuilderServices doctorBuilderService = new DoctorBuilderServices(userInputService, userOutputService);
                PatientBuilderServices patientBuilderService = new PatientBuilderServices(userInputService, userOutputService);
                HospitalBuilderServices hospitalBuilderService = new HospitalBuilderServices(userInputService, doctorBuilderService, patientBuilderService, userOutputService);

                // Create Hospital *This is where heavy input code runs*
                Hospital userHospital = hospitalBuilderService.createHospital();

                // Print Out Hospital World
                userHospital.printOutHospitalWorld();

                // Treatment of Patients functionality ** BROKEN
//                treatPatients(userHospital, userInputService, userOutputService);

                // JSON Print to Console & Save to File
                writeJson(userHospital);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static boolean promptUserStartPoint(UserInputService hospitalInputService, UserOutputService userOutputService) {
        userOutputService.printMessage("You can load from existing file or rewrite");
        int input = hospitalInputService.getUserIntInput("Enter the number 1 load, 2 to rewrite");
        if (input != 1 && input != 2) {
            promptUserStartPoint(hospitalInputService, userOutputService);
        } else return input == 1;
        return false;
    }

    public static void readAndWriting(String[] args) {
        if (args.length > 0) {
            hospitalJSONFileName = args[0];
        }

        if (!new File(hospitalJSONFileName).exists()) {
            System.out.println("Can't Open File, it does not exist...");
            System.out.println("New File will be created with name " + hospitalJSONFileName);

            createNewFile(hospitalJSONFileName);
        }

    }

    public static void createNewFile(String newFileName) {
        try {
            File myObj = new File(newFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //     Add writeJson function to set up project dependencies and test file writing
    public static void writeJson(Object someObject) throws IOException {
        String json = new ObjectMapper().writeValueAsString(someObject);

        // single line JSON
        System.out.println(json);

        // formatted JSON
        String newJson = "";
        for (String s : json.split("},")) {
            newJson += s + "}\n";
        }
        System.out.println("Formatted JSON:\n" + newJson);

        //Write JSON File
        writeToFile("oscarfile", json);
    }

    public static void writeToFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write(text);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }
    }

    public static void treatPatients(Hospital hospital, UserInputService userInputService, UserOutputService userOutputService){
        userOutputService.printMessage("This is the treatment loop...");
        boolean keepTreating = true;

        while (keepTreating) {
            // this can definitely be optimized...
            for (Map.Entry<String, List<Doctor>> specialtyDepartment : hospital.getSpecialtyDirectory().entrySet()) {
                userOutputService.printMessage(specialtyDepartment.getKey());
                for (Doctor doctor : specialtyDepartment.getValue()) {
                    doctor.printDoctorPatientList();
                    int input = userInputService.getUserIntInput("Enter a number to treat one of the patients from above, any other number will continue");
                    input -= 1;
                    if (input > 0 && input < doctor.getPatientList().size()) {
                       userOutputService.printMessage("TREATING PATIENT: " + doctor.getSinglePatient(input).getName());
                       doctor.treatPatient(doctor.getSinglePatient(input), userOutputService);
                    }
                }
            }

            if (0 != userInputService.getUserIntInput("Start new treatment? enter 0 to continue")) keepTreating = false;
        }
        userOutputService.printMessage("...EXITED TREATMENT LOOP");
    }
}
