package by.epamtc;

import by.epamtc.entity.Candy;
import by.epamtc.entity.Ingredient;
import by.epamtc.entity.Value;
import by.epamtc.entity.type.ChocolateType;
import by.epamtc.parser.AbstractCandyParser;
import by.epamtc.parser.dom.CandyDomParser;
import by.epamtc.parser.sax.CandySaxParser;
import by.epamtc.parser.stax.CandyStaxParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

public class ParserTest {

    private AbstractCandyParser parser;

    private static Candy testCandy;

    private static File file;

    @BeforeClass
    public static void setUpFileName() throws FileNotFoundException {
        ClassLoader loader = ParserTest.class.getClassLoader();
        URL resource = loader.getResource("test.xml");
        file = new File(resource.getFile());

    }

    @BeforeClass
    public static void setUp() {
        testCandy = new Candy();
        testCandy.setName("Mars");
        testCandy.setEnergy(1000);
        testCandy.setProductionDate(LocalDate.parse("2021-06-12"));
        testCandy.setExpirationDate(LocalDate.parse("2021-09-15"));
        ChocolateType type = new ChocolateType();
        type.setKind("milk");
        type.setWithFilling(true);
        type.setUnsweetened(false);
        testCandy.addType(type);
        Ingredient ingredient = new Ingredient();
        ingredient.setName("sugar");
        ingredient.setWeight(112);
        ingredient.setKind("white");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("vanillin");
        ingredient2.setWeight(25);
        ingredient2.setKind("classic");
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setName("milk");
        ingredient3.setWeight(33);
        ingredient3.setKind("powdered");
        testCandy.addIngredient(ingredient);
        testCandy.addIngredient(ingredient2);
        testCandy.addIngredient(ingredient3);
        Value value = new Value();
        value.setProteins(10);
        value.setFats(13);
        value.setCarbohydrates(44);
        testCandy.setValue(value);
        testCandy.setProduction("Mars.Inc");
    }

    @Test
    public void testSaxParser() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        parser = new CandySaxParser();
        parser.parseCandies(inputStream);
        Set<Candy> candies = parser.getCandies();
        Assert.assertEquals(candies.toArray()[0], testCandy);
    }

    @Test
    public void testStaxParser() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        parser = new CandyStaxParser();
        parser.parseCandies(inputStream);
        Set<Candy> candies = parser.getCandies();
        Assert.assertEquals(candies.toArray()[0], testCandy);
    }

    @Test
    public void testDomParser() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        parser = new CandyDomParser();
        parser.parseCandies(inputStream);
        Set<Candy> candies = parser.getCandies();
        Assert.assertEquals(candies.toArray()[0], testCandy);
    }

}
