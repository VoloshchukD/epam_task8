package by.epamtc.parser.sax;

import by.epamtc.entity.Candy;
import by.epamtc.parser.CandyXmlTag;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.entity.type.IrisType;
import by.epamtc.entity.type.LollipopsType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CandyContentHandler extends DefaultHandler {

    private Candy currentCandy;

    private Set<Candy> candies;

    private CandyXmlTag currentXmlTag;

    public CandyContentHandler() {
        candies = new HashSet<>();
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (CandyXmlTag.CANDY.toString().equals(qName)) {
            currentCandy = new Candy();
        } else if (CandyXmlTag.CHOCOLATE.toString().equals(qName)) {
            ChocolateType currentType = new ChocolateType();
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttributeName = attributes.getQName(i);
                if (currentAttributeName.equals(CandyXmlTag.WITH_FILLING.toString())) {
                    currentType.setWithFilling(Boolean.parseBoolean(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.UNSWEETENED.toString())) {
                    currentType.setUnsweetened(Boolean.parseBoolean(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.VARIETY.toString())) {
                    currentType.setVariety(attributes.getValue(i));
                }
            }
            currentCandy.addType(currentType);
        } else if (CandyXmlTag.IRIS.toString().equals(qName)) {
            IrisType currentType = new IrisType();
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttributeName = attributes.getQName(i);
                if (currentAttributeName.equals(CandyXmlTag.WITH_FLAVOR.toString())) {
                    currentType.setWithFlavor(Boolean.parseBoolean(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.CONSISTENCY.toString())) {
                    IrisType.IrisConsistency consistencyAsEnum =
                            IrisType.IrisConsistency.valueOf(attributes.getValue(i).toUpperCase());
                    currentType.setConsistency(consistencyAsEnum);
                } else if (currentAttributeName.equals(CandyXmlTag.VARIETY.toString())) {
                    currentType.setVariety(attributes.getValue(i));
                }
            }
            currentCandy.addType(currentType);
        } else if (CandyXmlTag.LOLLIPOPS.toString().equals(qName)) {
            LollipopsType currentType = new LollipopsType();
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttributeName = attributes.getQName(i);
                if (currentAttributeName.equals(CandyXmlTag.FILLING.toString())) {
                    LollipopsType.LollipopFilling fillingAsEnum =
                            LollipopsType.LollipopFilling.valueOf(attributes.getValue(i).toUpperCase());
                    currentType.setFilling(fillingAsEnum);
                } else if (currentAttributeName.equals(CandyXmlTag.THING_EMBEDDED.toString())) {
                    LollipopsType.LollipopThingsEmbedded thingsEmbeddedAsEnum =
                            LollipopsType.LollipopThingsEmbedded.valueOf(attributes.getValue(i).toUpperCase());
                    currentType.setThingsEmbedded(thingsEmbeddedAsEnum);
                } else if (currentAttributeName.equals(CandyXmlTag.VARIETY.toString())) {
                    currentType.setVariety(attributes.getValue(i));
                } else if (currentAttributeName.equals(CandyXmlTag.ON_STICK.toString())) {
                    currentType.setOnStick(Boolean.parseBoolean(attributes.getValue(i)));
                }
            }
            currentCandy.addType(currentType);
        } else if (CandyXmlTag.INGREDIENT.toString().equals(qName)) {
            Ingredient ingredient = new Ingredient();
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttributeName = attributes.getQName(i);
                if (currentAttributeName.equals(CandyXmlTag.INGREDIENT_NAME.toString())) {
                    ingredient.setName(attributes.getValue(i));
                } else if (currentAttributeName.equals(CandyXmlTag.WEIGHT.toString())) {
                    ingredient.setWeight(Integer.parseInt(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.KIND.toString())) {
                    ingredient.setKind(attributes.getValue(i));
                }
            }
            currentCandy.addIngredient(ingredient);
        } else if (CandyXmlTag.VALUE.toString().equals(qName)) {
            Value value = new Value();
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttributeName = attributes.getQName(i);
                if (currentAttributeName.equals(CandyXmlTag.PROTEINS.toString())) {
                    value.setProteins(Integer.parseInt(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.FATS.toString())) {
                    value.setFats(Integer.parseInt(attributes.getValue(i)));
                } else if (currentAttributeName.equals(CandyXmlTag.CARBOHYDRATES.toString())) {
                    value.setCarbohydrates(Integer.parseInt(attributes.getValue(i)));
                }
            }
            currentCandy.setValue(value);
        } else {
            currentXmlTag = CandyXmlTag.valueOfTag(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (CandyXmlTag.CANDY.toString().equals(qName)) {
            candies.add(currentCandy);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        data = data.replaceAll("\\s+", "");
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case CANDY_NAME:
                    currentCandy.setName(data);
                    break;
                case PRODUCTION_DATE:
                    DateTimeFormatter productionDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    currentCandy.setProductionDate(LocalDate.parse(data, productionDateFormatter));
                    break;
                case EXPIRATION_DATE:
                    DateTimeFormatter expirationDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    currentCandy.setExpirationDate(LocalDate.parse(data, expirationDateFormatter));
                    break;
                case ENERGY:
                    currentCandy.setEnergy(Integer.parseInt(data));
                    break;
                case PRODUCTION:
                    currentCandy.setProduction(data);
                    break;
            }
        }
        currentXmlTag = null;
    }

}
