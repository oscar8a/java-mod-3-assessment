package hospitalobjects;

public class Patient {
    private final String name;
    private final Ailment myDisease;

    public Patient(String nameInput, Ailment diseaseInput){
        name = nameInput;
        myDisease = diseaseInput;
    }

}
