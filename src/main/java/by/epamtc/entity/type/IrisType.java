package by.epamtc.entity.type;

public class IrisType extends CandyType {

    private boolean withFlavor;

    private String consistency;

    public boolean isWithFlavor() {
        return withFlavor;
    }

    public void setWithFlavor(boolean withFlavor) {
        this.withFlavor = withFlavor;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IrisType irisType = (IrisType) o;
        return withFlavor == irisType.withFlavor
                && (consistency == irisType.consistency
                || (consistency != null && consistency.equals(irisType.consistency)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + (withFlavor ? 0 : 1);
        result = 17 * result + ((consistency != null) ? consistency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@kind=" + super.getKind() +
                "withFlavor=" + withFlavor +
                ", consistency=" + consistency;
    }

}
