package by.epamtc.entity.type;

public class LollipopsType extends CandyType {

    private static final LollipopThingsEmbedded DEFAULT_THINGS_EMBEDDED = LollipopThingsEmbedded.NONE;

    private boolean onStick;

    private LollipopThingsEmbedded thingsEmbedded = DEFAULT_THINGS_EMBEDDED;

    private LollipopFilling filling;

    public enum LollipopFilling {

        BUBBLEGUM,
        SOFT_CANDY,
        NONE,
        LONG;

        private static final String ENUM_WORDS_SEPARATOR = "_";

        private static final String TAG_WORDS_SEPARATOR = "-";

        public static LollipopsType.LollipopFilling valueOfAttribute(String attributeName) {
            String enumValueAsString = attributeName.replaceAll(
                    TAG_WORDS_SEPARATOR, ENUM_WORDS_SEPARATOR).toUpperCase();
            LollipopsType.LollipopFilling enumValue = LollipopsType.LollipopFilling.valueOf(enumValueAsString);
            return enumValue;
        }

    }

    public enum LollipopThingsEmbedded {

        INSECT,
        FLASHING_LIGHT,
        MOTORIZED_DEVICE,
        NONE;

        private static final String ENUM_WORDS_SEPARATOR = "_";

        private static final String TAG_WORDS_SEPARATOR = "-";

        public static LollipopsType.LollipopThingsEmbedded valueOfAttribute(String attributeName) {
            String enumValueAsString = attributeName.replaceAll(
                    TAG_WORDS_SEPARATOR, ENUM_WORDS_SEPARATOR).toUpperCase();
            LollipopsType.LollipopThingsEmbedded enumValue = LollipopsType.LollipopThingsEmbedded.valueOf(enumValueAsString);
            return enumValue;
        }

    }

    public boolean isOnStick() {
        return onStick;
    }

    public void setOnStick(boolean onStick) {
        this.onStick = onStick;
    }

    public LollipopThingsEmbedded getThingsEmbedded() {
        return thingsEmbedded;
    }

    public void setThingsEmbedded(LollipopThingsEmbedded thingsEmbedded) {
        this.thingsEmbedded = thingsEmbedded;
    }

    public LollipopFilling getFilling() {
        return filling;
    }

    public void setFilling(LollipopFilling filling) {
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
