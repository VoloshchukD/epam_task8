package by.epamtc.entity.type;

public class ChocolateType extends CandyType {

    private boolean withFilling;

    private boolean unsweetened;

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

}
