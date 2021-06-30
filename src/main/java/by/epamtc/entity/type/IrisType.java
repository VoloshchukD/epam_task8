package by.epamtc.entity.type;

public class IrisType extends CandyType {

    private static final boolean DEFAULT_WITH_FLAVOR = false;

    private static final IrisConsistency DEFAULT_CONSISTENCY = IrisConsistency.SEMI_SOLID;

    private boolean withFlavor = DEFAULT_WITH_FLAVOR;

    private IrisConsistency consistency = DEFAULT_CONSISTENCY;

    public enum IrisConsistency {

        CAST_SEMI_SOLID,
        REPLICATED,
        SEMI_SOLID,
        SOFT,
        VISCOUS;

    }

    public boolean isWithFlavor() {
        return withFlavor;
    }

    public void setWithFlavor(boolean withFlavor) {
        this.withFlavor = withFlavor;
    }

    public IrisConsistency getConsistency() {
        return consistency;
    }

    public void setConsistency(IrisConsistency consistency) {
        this.consistency = consistency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IrisType irisType = (IrisType) o;
        return withFlavor == irisType.withFlavor && (consistency == irisType.consistency
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
                "@variety=" + super.getVariety() +
                "withFlavor=" + withFlavor +
                ", consistency=" + consistency;
    }

}
