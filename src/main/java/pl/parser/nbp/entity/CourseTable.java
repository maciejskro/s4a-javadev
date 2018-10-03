package pl.parser.nbp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private LocalDate notificationDate;
    @XmlElement(name = "data_publikacji")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private LocalDate publicationDate;
    @XmlElement(name = "pozycja" , type = Position.class)
    private List<Position> positons = new ArrayList<>();
}
