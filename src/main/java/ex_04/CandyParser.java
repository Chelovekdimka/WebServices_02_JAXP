package ex_04;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CandyParser {

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("src/main/java/ex_04/git initcandies.xml"));
        List<Candy> candies = new ArrayList<>();
        Candy candy = null;

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                if (elementName.equals("candy")) {
                    candy = new Candy();
                } else if (elementName.equals("name")) {
                    event = eventReader.nextEvent();
                    candy.setName(event.asCharacters().getData());
                } else if (elementName.equals("description")) {
                    event = eventReader.nextEvent();
                    candy.setDescription(event.asCharacters().getData());
                } else if (elementName.equals("price")) {
                    event = eventReader.nextEvent();
                    candy.setPrice(Double.parseDouble(event.asCharacters().getData()));
                }
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String elementName = endElement.getName().getLocalPart();

                if (elementName.equals("candy")) {
                    candies.add(candy);
                }
            }
        }

        System.out.println(candies);
    }

}