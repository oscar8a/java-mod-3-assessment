import inputservices.*;

import java.util.Scanner;

public class HospitalInputService implements UserInputService {
    private final Scanner scanner;
    private final UserOutputService userOutputService;

    public HospitalInputService(UserOutputService userOutputService) {
        this.scanner = new Scanner(System.in);
        this.userOutputService = userOutputService;
    }

    public String getUserStringInput(String prompt) {
        userOutputService.printMessage(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return getUserStringInput(prompt);
        }
        return input;
    }

    public int getUserIntInput(String prompt) {
        userOutputService.printMessage(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return getUserIntInput(prompt);
        }
        return Integer.parseInt(input);
    }

    public void close() throws Exception {
        scanner.close();
    }
}
