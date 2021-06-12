package by.epamtc.parser.sax;

import by.epamtc.entity.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class CandyContentHandler extends DefaultHandler {

    private Candy currentCandy;

    private Set<Candy> candies;

    private CandyXmlTag currentXmlTag;

    private EnumSet<CandyXmlTag> candyTags;

    private static final String ELEMENT_NAME = CandyXmlTag.CANDY.toString().toLowerCase();

    public CandyContentHandler() {
        candies = new HashSet<Candy>();
        candyTags = EnumSet.range(CandyXmlTag.CANDIES, CandyXmlTag.PRODUCTION);
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (ELEMENT_NAME.equals(qName)) {
            currentCandy = new Candy();
            setAttributes(attributes);
        } else {
            CandyXmlTag temp = CandyXmlTag.valueOf(qName.toUpperCase());
            if (candyTags.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void setAttributes(Attributes attributes) {
//        if (ELEMENT_STUDENT.equals(qName)) {
//            current = new Student();
//            current.setLogin(attrs.getValue(0));
//            if (attrs.getLength() == 2) { // warning!!!!
//                current.setFaculty(attrs.getValue(1));
//            }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (ELEMENT_NAME.equals(qName)) {
            candies.add(currentCandy);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        data = data.replaceAll("\\s+","");
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    currentCandy.setName(data);
                    break;
                case ENERGY:
                    currentCandy.setEnergy(Integer.parseInt(data));
                    break;
                case TYPES:
                    currentCandy.setTypes(new HashSet<>());
                    break;
//                case TYPE:
//                    currentCandy.getTypes().add();
//                    break;
//                case INGREDIENTS:
//                    break;
//                case VALUE:
//                    break;
//                case PRODUCTION:
//                    break;
                default:
//              TODO  default:
//                    throw new EnumConstantNotPresentException(
//                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

}
