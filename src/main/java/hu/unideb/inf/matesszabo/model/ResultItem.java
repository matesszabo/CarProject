package hu.unideb.inf.matesszabo.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "name",
                "price",
                "info",
                "miles",
                "location",
                "exteriorColor",
                "interiorColor",
                "VIN"
        }
)
public class ResultItem {

    @XmlAttribute(required = true)
    private String uri;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String info;

    @XmlElement(required = true)
    private Integer miles;

    @XmlElement(required = true)
    private String location;

    @XmlElement
    private String exteriorColor;

    @XmlElement
    private String interiorColor;

    @XmlElement(required = true)
    private String VIN;

    @XmlElement
    private Price price;

    public ResultItem(String uri, String name, String info, Integer miles, String location, String exteriorColor, String interiorColor, String VIN, Price price) {
        this.uri = uri;
        this.name = name;
        this.info = info;
        this.miles = miles;
        this.location = location;
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.VIN = VIN;
        this.price = price;
    }

    public ResultItem() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
}
