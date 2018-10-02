package pl.parser.nbp.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "pozycja")
public class Position {

    @XmlElement(name = "nazwa_waluty")
    String name;
    @XmlElement(name = "przelicznik")
    Double ratio;
    @XmlElement(name = "kod_waluty")
    String code;
    @XmlElement(name = "kurs_kupna")
    BigDecimal buy;
    @XmlElement(name = "kurs_sprzedazy")
    BigDecimal sell;
}
