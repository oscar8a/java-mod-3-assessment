package hospitalobjects;

public class Ailment {
    String name;
    String associatedSpecialty;
    int healthIndex;
    private final boolean curable;

    public Ailment(String diseaseName, String inputAssociatedSpecialty, int inputHealthIndex, boolean inputCurable) {
        name = diseaseName;
        associatedSpecialty = inputAssociatedSpecialty;
        healthIndex = inputHealthIndex;
        curable = inputCurable;
    }

    public String getAssociatedSpecialty() {
        return associatedSpecialty;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public String getName() {
        return name;
    }
}
