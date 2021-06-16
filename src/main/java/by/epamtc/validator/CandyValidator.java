package by.epamtc.validator;

import by.epamtc.parser.sax.CandyErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class CandyValidator {

    private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private static final Logger logger = LogManager.getLogger();

    public static boolean validate(File targetFile, File schemaFile) {
        boolean valid = true;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(targetFile);
            validator.setErrorHandler(new CandyErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            valid = false;
            logger.log(Level.ERROR, "Error while validating " + e.getMessage());
        }
        return valid;
    }

}

