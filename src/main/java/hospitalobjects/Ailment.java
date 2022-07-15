package hospitalobjects;

public class Ailment {
    String name;
    String associatedSpecialty;
    int healthIndex;

    public Ailment(String inputName, String inputAssociatedSpecialty, int inputHealthIndex) {
        name = inputName;
        associatedSpecialty = inputAssociatedSpecialty;
        healthIndex = inputHealthIndex;
    }
}
