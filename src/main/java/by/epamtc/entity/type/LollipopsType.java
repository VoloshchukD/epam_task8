package by.epamtc.entity.type;

public class LollipopsType extends CandyType {

    private boolean onStick;

    private String thingsEmbedded;

    private String filling;

    public boolean isOnStick() {
        return onStick;
    }

    public void setOnStick(boolean onStick) {
        this.onStick = onStick;
    }


    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public String getThingsEmbedded() {
        return thingsEmbedded;
    }

    public void setThingsEmbedded(String thingsEmbedded) {
        this.thingsEmbedded = thingsEmbedded;
    }
}
