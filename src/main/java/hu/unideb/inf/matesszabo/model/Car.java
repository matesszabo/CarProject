package hu.unideb.inf.matesszabo.model;

import javax.xml.bind.annotation.*;

/**
 * Created by mates on 2018. 01. 02..
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {
                "name",
                "price",
                "trim",
                "exteriorColor",
                "interiorColor",
                "mileage",
                "ctyMpg",
                "hwyMpg",
                "transmission",
                "engine",
                "driveType",
                "fuelType",
                "includedFeatures",
                "image",
                "place"
        }
)
public class Car {

    @XmlAttribute(required = true)
    private String uri;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private Price price;
    @XmlElement(required = false)
    private String trim;
    @XmlElement(required = true)
    private String exteriorColor;
    @XmlElement(required = false)
    private String interiorColor;
    @XmlElement(required = false)
    @XmlSchemaType(name = "nonNegativeInteger")
    private Integer mileage;
    @XmlElement(required = false)
    private Integer ctyMpg;
    @XmlElement(required = false)
    private Integer hwyMpg;
    @XmlElement(required = false)
    private String transmission;
    @XmlElement(required = false)
    private String engine;
    @XmlElement(required = false)
    private String driveType;
    @XmlElement(required = false)
    private String fuelType;
    @XmlElement(required = false)
    private String includedFeatures;
    @XmlElement(required = false)
    private Image image;
    @XmlElement(required = false)
    private String place;

    public Car() {
    }

    public Car(String uri, String name, Price price, String trim, String exteriorColor, String interiorColor, Integer mileage, Integer ctyMpg, Integer hwyMpg, String transmission, String engine, String driveType, String fuelType, String includedFeatures, String sellerNotes, Image image, String place) {
        this.uri = uri;
        this.name = name;
        this.price = price;
        this.trim = trim;
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.mileage = mileage;
        this.ctyMpg = ctyMpg;
        this.hwyMpg = hwyMpg;
        this.transmission = transmission;
        this.engine = engine;
        this.driveType = driveType;
        this.fuelType = fuelType;
        this.includedFeatures = includedFeatures;
        this.image = image;
        this.place = place;
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

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
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

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getCtyMpg() {
        return ctyMpg;
    }

    public void setCtyMpg(Integer ctyMpg) {
        this.ctyMpg = ctyMpg;
    }

    public Integer getHwyMpg() {
        return hwyMpg;
    }

    public void setHwyMpg(Integer hwyMpg) {
        this.hwyMpg = hwyMpg;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getIncludedFeatures() {
        return includedFeatures;
    }

    public void setIncludedFeatures(String includedFeatures) {
        this.includedFeatures = includedFeatures;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
