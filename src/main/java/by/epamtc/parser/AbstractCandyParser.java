package by.epamtc.parser;

import by.epamtc.entity.Candy;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCandyParser {

    protected Set<Candy> candies;

    public AbstractCandyParser() {
        candies = new HashSet<Candy>();
    }

    public abstract void parseCandies(String fileName);

    public Set<Candy> getCandies() {
        return candies;
    }

}
