package inputservices;

public interface UserInputService extends AutoCloseable {
    String getUserStringInput(String prompt);
    int getUserIntInput(String prompt);

    void close() throws Exception;
}
