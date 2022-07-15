import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import hospitalobjects.Doctor;
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
//                DoctorBuilderServices doctorBuilderService = new DoctorBuilderServices(userInputService, userOutputService);
//                PatientBuilderServices patientBuilderService = new PatientBuilderServices(userInputService, userOutputService);
//                HospitalBuilderServices hospitalBuilderService = new HospitalBuilderServices(userInputService, doctorBuilderService, patientBuilderService, userOutputService);

                // Create Hospital *This is where heavy input code runs*
//                Hospital userHospital = hospitalBuilderService.createHospital();

                //SKIP TO HERE, to load hospital directory from file
                Hospital restoredHospital = new ObjectMapper().readValue(new File(hospitalJSONFileName), Hospital.class);

                // Print Out Hospital World
//                userHospital.printOutHospitalWorld();
                restoredHospital.printOutHospitalWorld();

                // Print as JSON
//                writeJson(userHospital);




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
}
