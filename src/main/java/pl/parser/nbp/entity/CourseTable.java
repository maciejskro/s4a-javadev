package pl.parser.nbp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name="tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseTable {
    @XmlElement(name = "numer_tabeli")
    private String tableNumber;
    @XmlAttribute(name = "typ")
    private String type;
    @XmlElement(name = "data_notowania")
    private LocalDate notificationDate;
    @XmlElement(name = "data_publikacji")
    private LocalDate publicationDate;
    @XmlElement(name = "pozycja")
    private List<Position> positons;
}
