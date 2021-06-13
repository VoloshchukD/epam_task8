package by.epamtc.parser.dom;

import by.epamtc.entity.Candy;
import by.epamtc.entity.CandyType;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.sax.CandyXmlTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CandyDomParser extends AbstractCandyParser {

    private DocumentBuilder docBuilder;

    public CandyDomParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace(); //TODO log
        }
    }

    @Override
    public void parseCandies(String fileName) {
        try {
            Document document = docBuilder.parse(fileName);
            Element rootElement = document.getDocumentElement();
            NodeList candiesList = rootElement.getElementsByTagName(
                    CandyXmlTag.CANDY.toTagName());
            for (int i = 0; i < candiesList.getLength(); i++){
                Element currentElement = (Element) candiesList.item(i);
                Candy currentCandy = buildCandy(currentElement);
                candies.add(currentCandy);
            }

        } catch (SAXException e) {
            e.printStackTrace(); //TODO log
        } catch (IOException e) {
            e.printStackTrace(); //TODO log
        }
    }

    private Candy buildCandy(Element candyElement) {
        Candy candy = new Candy();
        candy.setName(getElementTextContent(candyElement, CandyXmlTag.CANDY_NAME.toTagName()));
        candy.setEnergy(Integer.parseInt(getElementTextContent(candyElement, CandyXmlTag.ENERGY.toTagName())));
        candy.setProduction(getElementTextContent(candyElement, CandyXmlTag.PRODUCTION.toTagName()));

        NodeList typesList = candyElement.getElementsByTagName(CandyXmlTag.TYPE.toTagName());
        for (int i = 0; i < typesList.getLength(); i++) {
            Element currentElement = (Element) typesList.item(i);
            CandyType candyType = buildCandyType(currentElement);
            candy.getTypes().add(candyType);
        }

        NodeList ingredientsList = candyElement.getElementsByTagName(CandyXmlTag.INGREDIENT.toTagName());
        for (int i = 0; i < ingredientsList.getLength(); i++) {
            Element currentElement = (Element) ingredientsList.item(i);
            Ingredient ingredient = buildIngredient(currentElement);
            candy.getIngredients().add(ingredient);
        }

        Element valuElement = (Element) candyElement.getElementsByTagName(CandyXmlTag.VALUE.toTagName()).item(0);
        Value value = new Value();
        value.setProteins(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.PROTEINS.toTagName())));
        value.setFats(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.FATS.toTagName())));
        value.setCarbohydrates(Integer.parseInt(valuElement.getAttribute(CandyXmlTag.CARBOHYDRATES.toTagName())));
        candy.setValue(value);

        return candy;
    }

    private CandyType buildCandyType(Element typeElement) {
        CandyType candyType = new CandyType();
        candyType.setName(typeElement.getAttribute(CandyXmlTag.TYPE_NAME.toTagName()));
        candyType.setWithFilling(Boolean.parseBoolean(
                typeElement.getAttribute(CandyXmlTag.WITH_FILLING.toTagName())));
        candyType.setConsistency(typeElement.getAttribute(CandyXmlTag.CONSISTENCY.toTagName()));
        candyType.setPreparation(typeElement.getAttribute(CandyXmlTag.PREPARATION.toTagName()));
        candyType.setSpecies(typeElement.getAttribute(CandyXmlTag.SPECIES.toTagName()));
        return candyType;
    }

    private Ingredient buildIngredient(Element currentElement) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(currentElement.getAttribute(CandyXmlTag.INGREDIENT_NAME.toTagName()));
        ingredient.setWeight(Integer.parseInt(currentElement.getAttribute(CandyXmlTag.WEIGHT.toTagName())));
        ingredient.setKind(currentElement.getAttribute(CandyXmlTag.KIND.toTagName()));
        return ingredient;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
