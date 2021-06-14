package by.epamtc.entity.type;

public class IrisType extends CandyType {

    private boolean withFlavor;

    private String consistency;

    public boolean isFlavored() {
        return withFlavor;
    }

    public void setFlavored(boolean flavored) {
        withFlavor = flavored;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }
}
