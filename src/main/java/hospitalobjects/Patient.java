package hospitalobjects;

public class Patient {
    private final String name;
    private final Ailment patientAilment;
    private int patientHealthIndex;

    public Patient(String nameInput, Ailment diseaseInput){
        name = nameInput;
        patientAilment = diseaseInput;
        patientHealthIndex = diseaseInput.getHealthIndex();
    }

    public String getName() {
        return name;
    }

    public void setPatientHealthIndex(int patientHealthIndex) {
        this.patientHealthIndex = patientHealthIndex;
    }

    public Ailment getPatientAilment() {
        return patientAilment;
    }

    public int getPatientHealthIndex() {
        return patientHealthIndex;
    }
}
