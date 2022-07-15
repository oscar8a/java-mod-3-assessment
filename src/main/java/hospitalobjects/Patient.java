package hospitalobjects;

public class Patient {
    private String name;
    private Ailment patientAilment;
    private int patientHealthIndex;

    public Patient(String nameInput, Ailment diseaseInput){
        name = nameInput;
        patientAilment = diseaseInput;
        patientHealthIndex = diseaseInput.getHealthIndex();
    }

    public String getName() {
        return name;
    }

    public Patient() {
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
