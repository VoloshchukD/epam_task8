package by.epamtc.parser.stax;

import by.epamtc.entity.Candy;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.entity.type.IrisType;
import by.epamtc.entity.type.LollipopsType;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.entity.CandyXmlTag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CandyStaxParser extends AbstractCandyParser {

    private XMLInputFactory inputFactory;

    public CandyStaxParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void parseCandies(String fileName) {
        //TODO file name null
        File file = new File(fileName);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (CandyXmlTag.CANDY.toTagName().equals(tagName)) {
                        Candy candy = buildCandy(reader);
                        candies.add(candy);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //TODO log
        } catch (XMLStreamException e) {
            e.printStackTrace(); //TODO log
        }
    }

    private Candy buildCandy(XMLStreamReader reader) throws XMLStreamException {
        Candy candy = new Candy();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String tagName = reader.getLocalName();
                    switch (CandyXmlTag.valueOfTag(tagName)) {
                        case CANDY_NAME:
                            candy.setName(getXMLText(reader));
                            break;
                        case ENERGY:
                            candy.setEnergy(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CHOCOLATE:
                            ChocolateType chocolateType = new ChocolateType();
                            chocolateType.setKind(reader.getAttributeValue(null,
                                    CandyXmlTag.KIND.toTagName()));
                            chocolateType.setWithFilling(Boolean.parseBoolean(
                                    reader.getAttributeValue(null, CandyXmlTag.WITH_FILLING.toTagName())));
                            chocolateType.setUnsweetened(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.UNSWEETENED.toTagName())));
                            chocolateType.setWithCoating(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.WITH_COATING.toTagName())));
                            candy.getTypes().add(chocolateType);
                            break;
                        case IRIS:
                            IrisType irisType = new IrisType();
                            irisType.setKind(reader.getAttributeValue(null,
                                    CandyXmlTag.KIND.toTagName()));
                            irisType.setConsistency(
                                    reader.getAttributeValue(null, CandyXmlTag.CONSISTENCY.toTagName()));
                            irisType.setFlavored(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.WITH_FLAVOR.toTagName())));
                            candy.getTypes().add(irisType);
                            break;
                        case LOLLIPOPS:
                            LollipopsType lollipopsType = new LollipopsType();
                            lollipopsType.setKind(reader.getAttributeValue(null,
                                    CandyXmlTag.KIND.toTagName()));
                            lollipopsType.setThingsEmbedded(
                                    reader.getAttributeValue(null, CandyXmlTag.THING_EMBEDDED.toTagName()));
                            lollipopsType.setFilling(reader.getAttributeValue(null,
                                    CandyXmlTag.FILLING.toTagName()));
                            lollipopsType.setOnStick(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.ON_STICK.toTagName())));
                            candy.getTypes().add(lollipopsType);
                            break;
                        case INGREDIENT:
                            Ingredient ingredient = new Ingredient();
                            ingredient.setName(reader.getAttributeValue(null,
                                    CandyXmlTag.INGREDIENT_NAME.toTagName()));
                            ingredient.setWeight(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.WEIGHT.toTagName())));
                            ingredient.setKind(reader.getAttributeValue(null,
                                    CandyXmlTag.KIND.toTagName()));
                            candy.getIngredients().add(ingredient);
                            break;
                        case VALUE:
                            Value value = new Value();
                            value.setProteins(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.PROTEINS.toTagName())));
                            value.setFats(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.FATS.toTagName())));
                            value.setCarbohydrates(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.CARBOHYDRATES.toTagName())));
                            candy.setValue(value);
                            break;
                        case PRODUCTION:
                            candy.setProduction(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    tagName = reader.getLocalName();
                    if (CandyXmlTag.CANDY.toTagName().equals(tagName)) {
                        return candy;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
