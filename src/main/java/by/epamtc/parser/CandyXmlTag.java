package by.epamtc.parser;

public enum CandyXmlTag {

    CANDIES,
    TYPES,
    CANDY,
    CANDY_NAME,
    ENERGY,
    PRODUCTION_DATE,
    EXPIRATION_DATE,
    CHOCOLATE,
    IRIS,
    LOLLIPOPS,
    WITH_FILLING,
    UNSWEETENED,
    VARIETY,
    KIND,
    WITH_FLAVOR,
    CONSISTENCY,
    ON_STICK,
    WITH_COATING,
    FILLING,
    THING_EMBEDDED,
    INGREDIENTS,
    INGREDIENT,
    PRODUCTION,
    TYPE_NAME,
    PREPARATION,
    SPECIES,
    INGREDIENT_NAME,
    WEIGHT,
    VALUE,
    PROTEINS,
    FATS,
    CARBOHYDRATES;

    private static final String ENUM_WORDS_SEPARATOR = "_";

    private static final String TAG_WORDS_SEPARATOR = "-";

    public static CandyXmlTag valueOfTag(String tag) {
        CandyXmlTag[] tags = CandyXmlTag.values();
        CandyXmlTag tagAsEnum = null;
        int i = 0;
        while (i < tags.length) {
            if (tags[i].toString().equals(tag)) {
                tagAsEnum = tags[i];
                break;
            }
            i++;
        }
        return tagAsEnum;
    }

    @Override
    public String toString() {
        String value = this.name().toLowerCase();
        value = value.replaceAll(ENUM_WORDS_SEPARATOR, TAG_WORDS_SEPARATOR);
        return value;
    }

}
