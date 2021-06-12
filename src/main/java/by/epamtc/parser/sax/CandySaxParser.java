package by.epamtc.parser.sax;

import by.epamtc.parser.AbstractCandyParser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class CandySaxParser extends AbstractCandyParser {

    private CandyContentHandler contentHandler;

    private XMLReader reader;

    public CandySaxParser() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); //TODO log
        }
        contentHandler = new CandyContentHandler();
        reader.setContentHandler(contentHandler);
        CandyErrorHandler errorHandler = new CandyErrorHandler();
        reader.setErrorHandler(errorHandler);
    }

    @Override
    public void parseCandies(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException e) {
            e.printStackTrace(); //TODO log
        } catch (SAXException e) {
            e.printStackTrace(); //TODO log
        }
        candies = contentHandler.getCandies();
    }

}
