package by.epamtc.validator;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.sax.CandyErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

public class CandyValidator {

    private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private static final Logger logger = LogManager.getLogger();

    public static boolean validate(InputStream targetInputStream, InputStream schemaInputStream)
            throws CandyParsingException {
        boolean valid = false;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Source schemaSource = new StreamSource(schemaInputStream);
            Schema schema = factory.newSchema(schemaSource);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(targetInputStream);
            validator.setErrorHandler(new CandyErrorHandler());
            validator.validate(source);
            valid = true;
        } catch (IOException e) {
            throw new CandyParsingException("Error while validating", e);
        } catch (SAXException e) {
            logger.warn("File is not valid", e);
        }
        return valid;
    }

}

