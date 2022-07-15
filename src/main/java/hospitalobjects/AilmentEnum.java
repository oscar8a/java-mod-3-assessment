package hospitalobjects;

public enum AilmentEnum {
    COLD("Pediatrics", 70),
    SKIN_RASH("Dermatology", 65),
    BROKEN_ARM("Orthopedics", 60);

    private final String specialtyNeeded;
    private int idx;

    AilmentEnum(String inputSpecialty, int inputIdx) {
        specialtyNeeded = inputSpecialty;
        idx = inputIdx;
    }

    public String getSpecialtyNeeded() {
        return specialtyNeeded;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }
}
