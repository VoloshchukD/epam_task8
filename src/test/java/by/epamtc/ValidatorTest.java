package by.epamtc;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.validator.CandyValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class ValidatorTest {

    private static final ClassLoader loader = ValidatorTest.class.getClassLoader();

    private FileInputStream schemaInputStream;

    private FileInputStream targetInputStream;

    private FileInputStream wrongTargetInputStream;

    @Before
    public void setUpFileName() throws FileNotFoundException {
        schemaInputStream = createInputStream("test.xsd");
        targetInputStream = createInputStream("test.xml");
        wrongTargetInputStream = createInputStream("wrong.xml");
    }

    private FileInputStream createInputStream(String fileName) throws FileNotFoundException {
        URL url = loader.getResource(fileName);
        String filePath = new File(url.getFile()).getAbsolutePath();
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    @Test
    public void testCandyValidator() throws CandyParsingException {
        Assert.assertTrue(CandyValidator.validate(targetInputStream, schemaInputStream));
    }

    @Test
    public void testCandyValidatorException() throws CandyParsingException {
        Assert.assertFalse(CandyValidator.validate(wrongTargetInputStream, schemaInputStream));
    }

}
