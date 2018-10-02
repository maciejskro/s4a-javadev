package pl.parser.nbp;

import pl.parser.nbp.entity.CourseTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;


public class XmlValidator {

    JAXBContext jaxbContext;
    Unmarshaller jaxbUnmarshaller;

    public XmlValidator() {
        try {
            jaxbContext = JAXBContext.newInstance(CourseTable.class);
            jaxbUnmarshaller= jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            System.out.println("Wrong xml format");
            System.err.println("wrong xml format");
        }
    }

    CourseTable getSingleTable(URL url) throws JAXBException {

        return (CourseTable) jaxbUnmarshaller.unmarshal(url);
    }
}