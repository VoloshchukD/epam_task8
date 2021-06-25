package by.epamtc.entity.type;

public class ChocolateType extends CandyType {

    private static final boolean DEFAULT_UNSWEETENED = false;

    private boolean withFilling;

    private boolean unsweetened = DEFAULT_UNSWEETENED;

    private boolean withCoating;

    public boolean isWithFilling() {
        return withFilling;
    }

    public void setWithFilling(boolean withFilling) {
        this.withFilling = withFilling;
    }

    public boolean isUnsweetened() {
        return unsweetened;
    }

    public void setUnsweetened(boolean unsweetened) {
        this.unsweetened = unsweetened;
    }

    public boolean isWithCoating() {
        return withCoating;
    }

    public void setWithCoating(boolean withCoating) {
        this.withCoating = withCoating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChocolateType that = (ChocolateType) o;
        return withFilling == that.withFilling
                && unsweetened == that.unsweetened
                && withCoating == that.withCoating;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 17 * result + (withFilling ? 0 : 1);
        result = 17 * result + (unsweetened ? 0 : 1);
        result = 17 * result + (withCoating ? 0 : 1);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@variety=" + super.getVariety() +
                ", withFilling=" + withFilling +
                ", unsweetened=" + unsweetened +
                ", withCoating=" + withCoating;
    }

}
