package hospitalobjects;

public class Ailment {
    String name;
    String associatedSpecialty;
    int healthIndex;

    public Ailment(String diseaseName, String inputAssociatedSpecialty, int inputHealthIndex) {
        name = diseaseName;
        associatedSpecialty = inputAssociatedSpecialty;
        healthIndex = inputHealthIndex;
    }

    public String getName() {
        return name;
    }
}
