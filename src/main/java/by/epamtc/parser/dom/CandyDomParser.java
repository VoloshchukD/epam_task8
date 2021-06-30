package by.epamtc.parser.dom;

import by.epamtc.entity.Candy;
import by.epamtc.entity.type.CandyType;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.entity.type.IrisType;
import by.epamtc.entity.type.LollipopsType;
import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.CandyXmlTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CandyDomParser extends AbstractCandyParser {

    private DocumentBuilder docBuilder;

    public CandyDomParser() throws CandyParsingException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new CandyParsingException("Error while creating DocumentBuilder", e);
        }
    }

    @Override
    public void parseCandies(InputStream inputStream) throws CandyParsingException {
        try {
            Document document = docBuilder.parse(inputStream);
            Element rootElement = document.getDocumentElement();
            NodeList candiesList = rootElement.getElementsByTagName(
                    CandyXmlTag.CANDY.toString());
            for (int i = 0; i < candiesList.getLength(); i++) {
                Element currentElement = (Element) candiesList.item(i);
                Candy currentCandy = buildCandy(currentElement);
                candies.add(currentCandy);
            }
        } catch (SAXException e) {
            throw new CandyParsingException("Error while DOM parsing", e);
        } catch (IOException e) {
            throw new CandyParsingException("IO exception while DOM parsing", e);
        }
    }

    private Candy buildCandy(Element candyElement) {
        Candy candy = new Candy();
        candy.setName(getElementTextContent(candyElement, CandyXmlTag.CANDY_NAME.toString()));
        candy.setEnergy(Integer.parseInt(getElementTextContent(candyElement, CandyXmlTag.ENERGY.toString())));
        candy.setProduction(getElementTextContent(candyElement, CandyXmlTag.PRODUCTION.toString()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        candy.setProductionDate(LocalDate.parse(getElementTextContent(candyElement,
                CandyXmlTag.PRODUCTION_DATE.toString()), formatter));
        candy.setExpirationDate(LocalDate.parse(getElementTextContent(candyElement,
                CandyXmlTag.EXPIRATION_DATE.toString()), formatter));

        Element chocolateTypesElement = (Element) candyElement.getElementsByTagName(
                CandyXmlTag.CHOCOLATE.toString()).item(0);
        Element irisTypesElement = (Element) candyElement.getElementsByTagName(
                CandyXmlTag.IRIS.toString()).item(0);
        Element lollipopsTypesElement = (Element) candyElement.getElementsByTagName(
                CandyXmlTag.LOLLIPOPS.toString()).item(0);

        if (chocolateTypesElement != null) {
            CandyType candyType = buildChocolateType(chocolateTypesElement);
            candy.addType(candyType);
        }
        if (irisTypesElement != null) {
            CandyType candyType = buildIrisType(irisTypesElement);
            candy.addType(candyType);
        }
        if (lollipopsTypesElement != null) {
            CandyType candyType = buildLollipopsType(lollipopsTypesElement);
            candy.addType(candyType);
        }

        NodeList ingredientsList = candyElement.getElementsByTagName(CandyXmlTag.INGREDIENT.toString());
        for (int i = 0; i < ingredientsList.getLength(); i++) {
            Element currentElement = (Element) ingredientsList.item(i);
            Ingredient ingredient = buildIngredient(currentElement);
            candy.addIngredient(ingredient);
        }

        Element valuElement = (Element) candyElement.getElementsByTagName(CandyXmlTag.VALUE.toString()).item(0);
        Value value = new Value();
        value.setProteins(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.PROTEINS.toString())));
        value.setFats(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.FATS.toString())));
        value.setCarbohydrates(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.CARBOHYDRATES.toString())));
        candy.setValue(value);

        return candy;
    }

    private CandyType buildChocolateType(Element typeElement) {
        ChocolateType candyType = new ChocolateType();
        candyType.setVariety(typeElement.getAttribute(CandyXmlTag.VARIETY.toString()));
        String unsweetened = typeElement.getAttribute(CandyXmlTag.UNSWEETENED.toString());
        if (!unsweetened.isEmpty()) {
            candyType.setUnsweetened(Boolean.parseBoolean(unsweetened));
        }
        candyType.setWithCoating(Boolean.parseBoolean(typeElement.getAttribute(CandyXmlTag.WITH_COATING.toString())));
        candyType.setWithFilling(Boolean.parseBoolean(typeElement.getAttribute(CandyXmlTag.WITH_FILLING.toString())));
        return candyType;
    }

    private CandyType buildIrisType(Element typeElement) {
        IrisType candyType = new IrisType();
        candyType.setVariety(typeElement.getAttribute(CandyXmlTag.VARIETY.toString()));
        String withFlavor = typeElement.getAttribute(CandyXmlTag.WITH_FLAVOR.toString());
        if (!withFlavor.isEmpty()) {
            candyType.setWithFlavor(Boolean.parseBoolean(withFlavor));
        }
        String consistency = typeElement.getAttribute(CandyXmlTag.CONSISTENCY.toString());
        if (!consistency.isEmpty()) {
            IrisType.IrisConsistency consistencyAsEnum =
                    IrisType.IrisConsistency.valueOf(consistency.toUpperCase());
            candyType.setConsistency(consistencyAsEnum);
        }
        return candyType;
    }

    private CandyType buildLollipopsType(Element typeElement) {
        LollipopsType candyType = new LollipopsType();
        candyType.setVariety(typeElement.getAttribute(CandyXmlTag.VARIETY.toString()));
        String filling = typeElement.getAttribute(CandyXmlTag.FILLING.toString());
        LollipopsType.LollipopFilling fillingAsEnum =
                LollipopsType.LollipopFilling.valueOf(filling.toUpperCase());
        candyType.setFilling(fillingAsEnum);
        candyType.setOnStick(Boolean.parseBoolean(typeElement.getAttribute(CandyXmlTag.ON_STICK.toString())));
        String thingsEmbedded = typeElement.getAttribute(CandyXmlTag.THING_EMBEDDED.toString());
        if (!thingsEmbedded.isEmpty()) {
            LollipopsType.LollipopThingsEmbedded thingsEmbeddedAsEnum =
                    LollipopsType.LollipopThingsEmbedded.valueOf(thingsEmbedded.toUpperCase());
            candyType.setThingsEmbedded(thingsEmbeddedAsEnum);
        }
        return candyType;
    }

    private Ingredient buildIngredient(Element currentElement) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(currentElement.getAttribute(CandyXmlTag.INGREDIENT_NAME.toString()));
        ingredient.setWeight(Integer.parseInt(currentElement.getAttribute(CandyXmlTag.WEIGHT.toString())));
        String kind = currentElement.getAttribute(CandyXmlTag.KIND.toString());
        if (!kind.isEmpty()) {
            ingredient.setKind(kind);
        }
        return ingredient;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
