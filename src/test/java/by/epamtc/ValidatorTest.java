package by.epamtc;

import by.epamtc.validator.CandyValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class ValidatorTest {

    private File schemaFile;

    private File targetFile;

    private File wrongTargetFile;

    @Before
    public void setUpFileName() {
        ClassLoader loader = ValidatorTest.class.getClassLoader();
        URL schemaUrl = loader.getResource("test.xsd");
        String schemaFileName = new File(schemaUrl.getFile()).getAbsolutePath();
        schemaFile = new File(schemaFileName);
        URL targetFileUrl = loader.getResource("test.xml");
        String targetFileName = new File(targetFileUrl.getFile()).getAbsolutePath();
        targetFile = new File(targetFileName);
        URL wrongTargetFileUrl = loader.getResource("wrong.xml");
        String wrongTargetFileName = new File(wrongTargetFileUrl.getFile()).getAbsolutePath();
        wrongTargetFile = new File(wrongTargetFileName);
    }

    @Test
    public void testCandyValidator() {
        Assert.assertTrue(CandyValidator.validate(targetFile, schemaFile));
    }

    @Test
    public void testCandyValidatorException() {
        Assert.assertFalse(CandyValidator.validate(wrongTargetFile, schemaFile));
    }

}
