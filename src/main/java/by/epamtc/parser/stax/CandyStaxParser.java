package by.epamtc.parser.stax;

import by.epamtc.entity.Candy;
import by.epamtc.entity.CandyType;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.sax.CandyXmlTag;

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
                        case TYPE:
                            CandyType candyType = new CandyType();
                            candyType.setName(reader.getAttributeValue(null,
                                    CandyXmlTag.TYPE_NAME.toTagName()));
                            candyType.setWithFilling(Boolean.parseBoolean(
                                    reader.getAttributeValue(null, CandyXmlTag.WITH_FILLING.toTagName())));
                            candyType.setConsistency(reader.getAttributeValue(null,
                                    CandyXmlTag.CONSISTENCY.toTagName()));
                            candyType.setPreparation(reader.getAttributeValue(null,
                                    CandyXmlTag.PREPARATION.toTagName()));
                            candyType.setSpecies(reader.getAttributeValue(null,
                                    CandyXmlTag.SPECIES.toTagName()));
                            candy.getTypes().add(candyType);
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
