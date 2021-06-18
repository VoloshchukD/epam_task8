package by.epamtc.parser.sax;

import by.epamtc.entity.Candy;
import by.epamtc.exception.NoSuchUnitException;
import by.epamtc.parser.CandyXmlTag;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.entity.type.IrisType;
import by.epamtc.entity.type.LollipopsType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CandyContentHandler extends DefaultHandler {

    private Candy currentCandy;

    private Set<Candy> candies;

    private CandyXmlTag currentXmlTag;

    public CandyContentHandler() {
        candies = new HashSet<Candy>();
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (CandyXmlTag.CANDY.toTagName().equals(qName)) {
            currentCandy = new Candy();
        } else if (CandyXmlTag.CHOCOLATE.compareToTag(qName)) {
            ChocolateType currentType = new ChocolateType();
            currentType.setWithFilling(Boolean.parseBoolean(attributes.getValue(0)));
            currentType.setUnsweetened(Boolean.parseBoolean(attributes.getValue(1)));
            currentType.setKind(attributes.getValue(2));
            currentCandy.getTypes().add(currentType);
        } else if (CandyXmlTag.IRIS.compareToTag(qName)) {
            IrisType currentType = new IrisType();
            currentType.setWithFlavor(Boolean.parseBoolean(attributes.getValue(0)));
            currentType.setConsistency(attributes.getValue(1));
            currentType.setKind(attributes.getValue(2));
            currentCandy.getTypes().add(currentType);
        } else if (CandyXmlTag.LOLLIPOPS.compareToTag(qName)) {
            LollipopsType currentType = new LollipopsType();
            currentType.setOnStick(Boolean.parseBoolean(attributes.getValue(0)));
            currentType.setFilling(attributes.getValue(1));
            currentType.setThingsEmbedded(attributes.getValue(2));
            currentType.setKind(attributes.getValue(3));
            currentCandy.getTypes().add(currentType);
        } else if (CandyXmlTag.INGREDIENT.toTagName().equals(qName)) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(attributes.getValue(0));
            ingredient.setWeight(Integer.parseInt(attributes.getValue(1)));
            ingredient.setKind(attributes.getValue(2) == null ? "" : attributes.getValue(2));
            currentCandy.getIngredients().add(ingredient);
        } else if (CandyXmlTag.VALUE.toTagName().equals(qName)) {
            Value value = new Value();
            value.setProteins(Integer.parseInt(attributes.getValue(0)));
            value.setFats(Integer.parseInt(attributes.getValue(1)));
            value.setCarbohydrates(Integer.parseInt(attributes.getValue(2)));
            currentCandy.setValue(value);
        } else {
            if (CandyXmlTag.contaisTag(qName)) {
                currentXmlTag = CandyXmlTag.valueOfTag(qName);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (CandyXmlTag.CANDY.toTagName().equals(qName)) {
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
//      TODO          default:
//                    throw new EnumConstantNotPresentException(
//                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());

            }
        }
        currentXmlTag = null;
    }

}
