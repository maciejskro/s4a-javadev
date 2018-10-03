package pl.parser.nbp.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    @XmlElement(name = "nazwa_waluty")
    private String name;
    @XmlElement(name = "przelicznik")
    private Double ratio;
    @XmlElement(name = "kod_waluty")
    private String code;
    @XmlElement(name = "kurs_kupna")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal buy;
    @XmlElement(name = "kurs_sprzedazy")
    @XmlJavaTypeAdapter(BigDecimalAdapter.class)
    private BigDecimal sell;
}
