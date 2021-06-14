package by.epamtc;

import by.epamtc.entity.Candy;
import by.epamtc.parser.dom.CandyDomParser;
import by.epamtc.parser.sax.CandySaxParser;
import by.epamtc.parser.stax.CandyStaxParser;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        CandyStaxParser parser = new CandyStaxParser();
        parser.parseCandies("data_xml/candies.xml");
        Set<Candy> candies = parser.getCandies();

        for (Candy candy:candies) {
            System.out.println(candy);
        }

    }
}
