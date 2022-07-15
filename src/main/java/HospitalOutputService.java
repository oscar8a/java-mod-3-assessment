import inputservices.UserOutputService;

public class HospitalOutputService implements UserOutputService {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
