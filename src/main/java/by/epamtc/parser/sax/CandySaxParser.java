package by.epamtc.parser.sax;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.AbstractCandyParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class CandySaxParser extends AbstractCandyParser {

    private CandyContentHandler contentHandler;

    private XMLReader reader;

    public CandySaxParser() throws CandyParsingException {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            throw new CandyParsingException("Error while creating SAXParser", e);
        }
        contentHandler = new CandyContentHandler();
        reader.setContentHandler(contentHandler);
        CandyErrorHandler errorHandler = new CandyErrorHandler();
        reader.setErrorHandler(errorHandler);
    }

    @Override
    public void parseCandies(InputStream inputStream) throws CandyParsingException {
        try {
            InputSource inputSource = new InputSource(inputStream);
            reader.parse(inputSource);
        } catch (IOException e) {
            throw new CandyParsingException("IOException while SAX parsing", e);
        } catch (SAXException e) {
            throw new CandyParsingException("SAXException while parse", e);
        }
        candies = contentHandler.getCandies();
    }

}
