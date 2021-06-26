package by.epamtc.parser;

import by.epamtc.exception.CandyParsingException;
import by.epamtc.parser.dom.CandyDomParser;
import by.epamtc.parser.sax.CandySaxParser;
import by.epamtc.parser.stax.CandyStaxParser;

public class CandyParserFactory {

    private enum ParserType {
        SAX, STAX, DOM
    }

    private CandyParserFactory() {
    }

    public static AbstractCandyParser createCandyParser(String parserName) throws CandyParsingException {
        ParserType type;
        try {
            type = ParserType.valueOf(parserName);
        } catch (IllegalArgumentException e) {
            throw new CandyParsingException("No such parser: " + parserName, e);
        }
        AbstractCandyParser parser = null;
        switch (type) {
            case SAX:
                parser = new CandySaxParser();
                break;
            case STAX:
                parser = new CandyStaxParser();
                break;
            case DOM:
                parser = new CandyDomParser();
                break;
        }
        return parser;
    }

}
