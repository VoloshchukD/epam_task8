package by.epamtc.entity.type;

public class LollipopsType extends CandyType {

    private static final String DEFAULT_THINGS_EMBEDDED = "none";

    private boolean onStick;

    private String thingsEmbedded = DEFAULT_THINGS_EMBEDDED;

    private String filling;

    public boolean isOnStick() {
        return onStick;
    }

    public void setOnStick(boolean onStick) {
        this.onStick = onStick;
    }

    public String getThingsEmbedded() {
        return thingsEmbedded;
    }

    public void setThingsEmbedded(String thingsEmbedded) {
        this.thingsEmbedded = thingsEmbedded;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LollipopsType that = (LollipopsType) o;
        return onStick == that.onStick
                && (thingsEmbedded == that.thingsEmbedded
                || (thingsEmbedded != null && thingsEmbedded.equals(that.thingsEmbedded)))
                && (filling == that.filling || (filling != null && filling.equals(that.filling)));
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 17 * result + (onStick ? 0 : 1);
        result = 17 * result + ((thingsEmbedded != null) ? thingsEmbedded.hashCode() : 0);
        result = 17 * result + ((filling != null) ? filling.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@variety=" + super.getVariety() +
                ", onStick=" + onStick +
                ", thingsEmbedded=" + thingsEmbedded +
                ", filling=" + filling;
    }

}
