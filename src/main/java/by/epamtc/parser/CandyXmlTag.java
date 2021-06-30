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
    INGREDIENT_NAME,
    WEIGHT,
    VALUE,
    PROTEINS,
    FATS,
    CARBOHYDRATES;

    private static final String ENUM_WORDS_SEPARATOR = "_";

    private static final String TAG_WORDS_SEPARATOR = "-";

    public static CandyXmlTag valueOfTag(String tag) {
        String enumValueAsString = tag.replaceAll(TAG_WORDS_SEPARATOR, ENUM_WORDS_SEPARATOR).toUpperCase();
        CandyXmlTag tagAsEnum = CandyXmlTag.valueOf(enumValueAsString);
        return tagAsEnum;
    }

    @Override
    public String toString() {
        String value = this.name().toLowerCase();
        value = value.replaceAll(ENUM_WORDS_SEPARATOR, TAG_WORDS_SEPARATOR);
        return value;
    }

}
