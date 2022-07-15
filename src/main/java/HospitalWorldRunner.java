import java.io.File;
import java.io.IOException;

import hospitalobjects.Hospital;
import inputservices.*;

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
                Hospital userHospital = new Hospital(userInputService.getUserStringInput("Please enter a name for your Hospital"));

                userOutputService.printMessage("Your Hospital Name is " + userHospital.getHospitalName());
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
            System.out.println("Do You Wish to create a new file?");


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
}
