package by.epamtc.parser;

import by.epamtc.parser.dom.CandyDomParser;
import by.epamtc.parser.sax.CandySaxParser;
import by.epamtc.parser.stax.CandyStaxParser;

public class CandyParserFactory {

    private enum ParserType {
        SAX, STAX, DOM
    }

    private CandyParserFactory() {
    }

    public static AbstractCandyParser createCandyParser(String parserName) {
        ParserType type = ParserType.valueOf(parserName);
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
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
        return parser;
    }

}
