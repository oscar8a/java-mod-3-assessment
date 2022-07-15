package hospitalobjects;

public class Specialty {
    String title;
    int numberOfTreatmentsToCure;

    public void setTitle(String title) {
        this.title = title;
    }

    public Specialty() {
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfTreatmentsToCure() {
        return numberOfTreatmentsToCure;
    }

    public void setNumberOfTreatmentsToCure(int numberOfTreatmentsToCure) {
        this.numberOfTreatmentsToCure = numberOfTreatmentsToCure;
    }
}
