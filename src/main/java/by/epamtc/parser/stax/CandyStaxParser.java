package by.epamtc.parser.stax;

import by.epamtc.entity.Candy;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.entity.type.IrisType;
import by.epamtc.entity.type.LollipopsType;
import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.CandyXmlTag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CandyStaxParser extends AbstractCandyParser {

    private XMLInputFactory inputFactory;

    public CandyStaxParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void parseCandies(InputStream inputStream) throws CandyParsingException {
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (CandyXmlTag.CANDY.toString().equals(tagName)) {
                        Candy candy = buildCandy(reader);
                        candies.add(candy);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new CandyParsingException("Error while Stax parsing", e);
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
                        case PRODUCTION_DATE:
                            DateTimeFormatter productionDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            candy.setProductionDate(LocalDate.parse(getXMLText(reader), productionDateFormatter));
                            break;
                        case EXPIRATION_DATE:
                            DateTimeFormatter expirationDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            candy.setExpirationDate(LocalDate.parse(getXMLText(reader), expirationDateFormatter));
                            break;
                        case CHOCOLATE:
                            ChocolateType chocolateType = new ChocolateType();
                            chocolateType.setVariety(reader.getAttributeValue(null,
                                    CandyXmlTag.VARIETY.toString()));
                            chocolateType.setWithFilling(Boolean.parseBoolean(
                                    reader.getAttributeValue(null, CandyXmlTag.WITH_FILLING.toString())));
                            String unsweetened = reader.getAttributeValue(null, CandyXmlTag.UNSWEETENED.toString());
                            if (unsweetened != null) {
                                chocolateType.setUnsweetened(Boolean.parseBoolean(unsweetened));
                            }
                            chocolateType.setWithCoating(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.WITH_COATING.toString())));
                            candy.addType(chocolateType);
                            break;
                        case IRIS:
                            IrisType irisType = new IrisType();
                            irisType.setVariety(reader.getAttributeValue(null,
                                    CandyXmlTag.VARIETY.toString()));
                            String consistency = reader.getAttributeValue(null, CandyXmlTag.CONSISTENCY.toString());
                            if (consistency != null) {
                                irisType.setConsistency(consistency);
                            }
                            String withFlavor = reader.getAttributeValue(null, CandyXmlTag.WITH_FLAVOR.toString());
                            if (withFlavor != null) {
                                irisType.setWithFlavor(Boolean.parseBoolean(withFlavor));
                            }
                            candy.addType(irisType);
                            break;
                        case LOLLIPOPS:
                            LollipopsType lollipopsType = new LollipopsType();
                            lollipopsType.setVariety(reader.getAttributeValue(null,
                                    CandyXmlTag.VARIETY.toString()));
                            String thingsEmbedded = reader.getAttributeValue(null, CandyXmlTag.THING_EMBEDDED.toString());
                            if (thingsEmbedded != null) {
                                lollipopsType.setThingsEmbedded(thingsEmbedded);
                            }
                            lollipopsType.setFilling(reader.getAttributeValue(null,
                                    CandyXmlTag.FILLING.toString()));
                            lollipopsType.setOnStick(Boolean.parseBoolean(reader.getAttributeValue(null,
                                    CandyXmlTag.ON_STICK.toString())));
                            candy.addType(lollipopsType);
                            break;
                        case INGREDIENT:
                            Ingredient ingredient = new Ingredient();
                            ingredient.setName(reader.getAttributeValue(null,
                                    CandyXmlTag.INGREDIENT_NAME.toString()));
                            ingredient.setWeight(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.WEIGHT.toString())));
                            String kind = reader.getAttributeValue(null, CandyXmlTag.KIND.toString());
                            if (kind != null) {
                                ingredient.setKind(kind);
                            }
                            candy.addIngredient(ingredient);
                            break;
                        case VALUE:
                            Value value = new Value();
                            value.setProteins(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.PROTEINS.toString())));
                            value.setFats(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.FATS.toString())));
                            value.setCarbohydrates(Integer.parseInt(reader.getAttributeValue(null,
                                    CandyXmlTag.CARBOHYDRATES.toString())));
                            candy.setValue(value);
                            break;
                        case PRODUCTION:
                            candy.setProduction(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    tagName = reader.getLocalName();
                    if (CandyXmlTag.CANDY.toString().equals(tagName)) {
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
