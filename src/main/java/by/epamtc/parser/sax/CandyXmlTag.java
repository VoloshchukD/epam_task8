package by.epamtc.parser.sax;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String MAKE_TAG_NAME_REGEX = "(.+)[_](.)(.*)";

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

    public String toTagName() {
        String value = this.name().toLowerCase();
        Pattern pattern = Pattern.compile(MAKE_TAG_NAME_REGEX);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            value = matcher.group(1) + matcher.group(2).toUpperCase() + matcher.group(3);
        }
        return value;
    }

}
