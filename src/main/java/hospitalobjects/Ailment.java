package hospitalobjects;

public class Ailment {
    String name;
    String associatedSpecialty;
    int healthIndex;
    private boolean curable;

    public Ailment(String diseaseName, String inputAssociatedSpecialty, int inputHealthIndex, boolean inputCurable) {
        name = diseaseName;
        associatedSpecialty = inputAssociatedSpecialty;
        healthIndex = inputHealthIndex;
        curable = inputCurable;
    }

    public Ailment() {
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
