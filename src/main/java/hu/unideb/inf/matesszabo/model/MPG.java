package hu.unideb.inf.matesszabo.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Created by mates on 2018. 01. 02..
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MPG {

    @XmlValue
    private BigDecimal value;

    @XmlAttribute(name = "unit", required = true)
    @XmlSchemaType(name = "token")
    private String unit;

    public MPG() {
    }

    public MPG(BigDecimal value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
