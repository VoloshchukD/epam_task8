package by.epamtc.parser.sax;

import by.epamtc.entity.Candy;
import by.epamtc.entity.CandyType;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
        } else if (CandyXmlTag.TYPE.compareToTag(qName)) {
            CandyType currentType = new CandyType();
            currentType.setName(attributes.getValue(0));
            currentType.setWithFilling(Boolean.parseBoolean(attributes.getValue(1)));
            currentType.setConsistency(attributes.getValue(2));
            currentType.setPreparation(attributes.getValue(3));
            currentType.setSpecies(attributes.getValue(4));
            currentCandy.getTypes().add(currentType);
        } else if (CandyXmlTag.INGREDIENT.toTagName().equals(qName)) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(attributes.getValue(0));
            ingredient.setWeight(Integer.parseInt(attributes.getValue(1)));
            ingredient.setKind(attributes.getValue(2));
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

    public void setAttributes(Attributes attributes) {
//        if (ELEMENT_STUDENT.equals(qName)) {
//            current = new Student();
//            current.setLogin(attrs.getValue(0));
//            if (attrs.getLength() == 2) { // warning!!!!
//                current.setFaculty(attrs.getValue(1));
//            }
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
                case ENERGY:
                    currentCandy.setEnergy(Integer.parseInt(data));
                    break;
                case TYPES:
                    currentCandy.setTypes(new HashSet<>());
                    break;
                case TYPE:
                    currentCandy.getTypes().add(new CandyType());
                    break;
                case INGREDIENTS:
                    currentCandy.setIngredients(new HashSet<>());
                    break;
                case INGREDIENT:
                    currentCandy.getIngredients().add(new Ingredient());
                    break;
                case VALUE:
                    currentCandy.setValue(new Value());
                    break;
                case PRODUCTION:
                    currentCandy.setProduction(data);
                    break;
                default:
//              TODO  default:
//                    throw new EnumConstantNotPresentException(
//                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

}
