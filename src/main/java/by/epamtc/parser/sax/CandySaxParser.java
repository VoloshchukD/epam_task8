package by.epamtc.parser.sax;

import by.epamtc.parser.AbstractCandyParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class CandySaxParser extends AbstractCandyParser {

    private CandyContentHandler contentHandler;

    private XMLReader reader;

    private static final Logger logger = LogManager.getLogger();

    public CandySaxParser() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, "Error while creating SAXParser " + e.getMessage());
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
            logger.log(Level.ERROR, "IOException while SAX parsing " + e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAXException while parse " + e.getMessage());
        }
        candies = contentHandler.getCandies();
    }

}
