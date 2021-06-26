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

    private FileInputStream schemaInputStream;

    private FileInputStream targetInputStream;

    private FileInputStream wrongTargetInputStream;

    @Before
    public void setUpFileName() throws FileNotFoundException {
        ClassLoader loader = ValidatorTest.class.getClassLoader();
        URL schemaUrl = loader.getResource("test.xsd");
        String schemaFileName = new File(schemaUrl.getFile()).getAbsolutePath();
        File schemaFile = new File(schemaFileName);
        schemaInputStream = new FileInputStream(schemaFile);

        URL targetFileUrl = loader.getResource("test.xml");
        String targetFileName = new File(targetFileUrl.getFile()).getAbsolutePath();
        File targetFile = new File(targetFileName);
        targetInputStream = new FileInputStream(targetFile);

        URL wrongTargetFileUrl = loader.getResource("wrong.xml");
        String wrongTargetFileName = new File(wrongTargetFileUrl.getFile()).getAbsolutePath();
        File wrongTargetFile = new File(wrongTargetFileName);
        wrongTargetInputStream = new FileInputStream(wrongTargetFile);
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
