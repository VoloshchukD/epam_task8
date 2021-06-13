package by.epamtc.parser.sax;

public enum CandyXmlTag {

    CANDIES,
    CANDY,
    CANDY_NAME,
    ENERGY,
    TYPES,
    TYPE,
    INGREDIENTS,
    INGREDIENT,
    PRODUCTION,
    TYPE_NAME,
    WITH_FILLING,
    CONSISTENCY,
    PREPARATION,
    SPECIES,
    INGREDIENT_NAME,
    WEIGHT,
    KIND,
    VALUE,
    PROTEINS,
    FATS,
    CARBOHYDRATES;

    private static final String ENUM_WORDS_SEPARATOR = "_";

    public boolean compareToTag(String tag) {
        //TODO tag == null
        String valueAsString = this.toString().replaceAll(ENUM_WORDS_SEPARATOR, ""); //TODO add regex
        String upperCaseTag = tag.toUpperCase();
        return valueAsString.equals(upperCaseTag);
    }

    public static boolean contaisTag(String tag) {
        CandyXmlTag tagAsEnum = valueOfTag(tag);
        return tagAsEnum != null;
    }

    public static CandyXmlTag valueOfTag(String tag) {
        CandyXmlTag[] tags = CandyXmlTag.values();
        CandyXmlTag tagAsEnum = null;
        int i = 0;
        while (i < tags.length){
            if (tags[i].compareToTag(tag)) {
                tagAsEnum = tags[i];
                break;
            }
            i++;
        }
        return tagAsEnum;
    }

}
