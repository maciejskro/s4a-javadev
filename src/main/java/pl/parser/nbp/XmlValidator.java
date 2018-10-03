package pl.parser.nbp;

import pl.parser.nbp.entity.CourseTable;
import pl.parser.nbp.entity.Position;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.Optional;


public class XmlValidator {

    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    public XmlValidator() {
        try {
            jaxbContext = JAXBContext.newInstance(CourseTable.class);
            jaxbUnmarshaller=  jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            System.err.println("wrong xml format");
        }
    }

    CourseTable getSingleTable(URL url) throws JAXBException {
        return (CourseTable) jaxbUnmarshaller.unmarshal(url);
    }

    Optional<Position> getSinglePositonFromTable(URL url, String code) throws JAXBException {
        CourseTable ct = getSingleTable(url);
        return ct.getPositons().stream().filter(position -> position.getCode().equals(code))
                .findFirst();
    }
}
