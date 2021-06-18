package by.epamtc.parser;

import by.epamtc.entity.Candy;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCandyParser {

    protected Set<Candy> candies;

    public AbstractCandyParser() {
        candies = new HashSet<Candy>();
    }

    public abstract void parseCandies(InputStream inputStream);

    public Set<Candy> getCandies() {
        return candies;
    }

}
