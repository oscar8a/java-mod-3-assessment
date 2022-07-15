import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

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
        try(UserInputService userInputService = new HospitalInputService(userOutputService)){
            boolean keepProgramRunning = true;

            while (keepProgramRunning) {
                userOutputService.printMessage("Starting HospitalWorldRunner...");

                // Instantiate Hospital Builder Services
                DoctorBuilderServices doctorBuilderService = new DoctorBuilderServices(userInputService);
                PatientBuilderServices patientBuilderService = new PatientBuilderServices(userInputService);
                HospitalBuilderServices hospitalBuilderService = new HospitalBuilderServices(userInputService, doctorBuilderService, patientBuilderService);

                // Create Hospital *This is where heavy input code runs*
                Hospital userHospital = hospitalBuilderService.createHospital();

                // Print Out Hospital World
                userHospital.printOutHospitalWorld();






                // run once for now
                keepProgramRunning = false;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    // Add writeJson function to set up project dependencies and test file writing
    public static void writeJson(Object someObject) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(someObject);
        System.out.println(json);
    }
}
