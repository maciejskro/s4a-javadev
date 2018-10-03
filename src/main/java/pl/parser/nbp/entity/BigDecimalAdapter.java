package pl.parser.nbp.entity;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        return new BigDecimal(v.replace(",","."));
    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return v.toString();
    }
}
