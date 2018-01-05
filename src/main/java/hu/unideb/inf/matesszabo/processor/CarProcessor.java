package hu.unideb.inf.matesszabo.processor;

import com.sun.media.sound.InvalidFormatException;
import hu.unideb.inf.matesszabo.model.Car;
import hu.unideb.inf.matesszabo.model.Image;
import hu.unideb.inf.matesszabo.model.Price;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by mates on 2018. 01. 02..
 */
public class CarProcessor {

    private static Logger logger = LoggerFactory.getLogger(CarListProcessor.class);


    public CarProcessor() {
    }

    public Car parse(Document doc, String uri) throws IOException {
        Car car = null;
        try {
            car = doProcess(doc);
            car.setUri(uri);
        } catch (Exception e) {
            throw new IOException("Malformed document");
        }

        return car;
    }

    private Car doProcess(Document doc) throws InvalidFormatException {
        logger.info("process started");
        Car car = new Car();
        parseName(doc, car);
        parsePrice(doc, car);
        parsePlace(doc, car);
        parseTrim(doc, car);
        parseExteriorColor(doc, car);
        parseInteriorColor(doc, car);
        parseMileage(doc, car);
        parseCtyMPG(doc, car);
        parseHwyMPG(doc, car);
        parseTransmission(doc, car);
        parseEngine(doc, car);
        parseDriveType(doc, car);
        parseFuelType(doc, car);
        parseIncludedFeatures(doc, car);
        parseImage(doc, car);


        return car;
    }

    private void parseName(Document doc, Car car) throws InvalidFormatException {
        car.setName(null);
        try {

            car.setName(doc.select("div.container>h1[data-qa=heading]>div.text-truncate").text().trim());
            logger.info("name set");
        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parsePrice(Document doc, Car car) throws InvalidFormatException {
        car.setPrice(null);
        try {
            logger.info(doc.select("div[data-qa=PricingBlock] > div.label-block-text > span:nth-child(1)").text().substring(1).replaceAll(",",""));
            //car.setPrice(new Price(new BigDecimal(doc.select(".label-block-text > span:nth-child(1)").text().substring(1).replaceAll(",","")),"USD"));
            car.setPrice(new Price(new BigDecimal(doc.select("div[data-qa=PricingBlock] > div.label-block-text > span:nth-child(1)").text().substring(1).replaceAll(",","")),"USD"));
            logger.info("Price set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parsePlace(Document doc, Car car) throws InvalidFormatException {
        car.setPlace(null);
        try {
            car.setPlace(doc.select("ul > li:nth-child(1) > strong:nth-child(1)").text().trim());
            logger.info("place set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseTrim(Document doc, Car car) throws InvalidFormatException {
        car.setTrim(null);
        try {
            car.setTrim(doc.select("div:nth-child(1) > div:nth-child(1) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("trim set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseExteriorColor(Document doc, Car car) throws InvalidFormatException {
        car.setExteriorColor(null);
        try {
            car.setExteriorColor(doc.select("div:nth-child(1) > div:nth-child(2) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("ecolor set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseInteriorColor(Document doc, Car car) throws InvalidFormatException {
        car.setInteriorColor(null);
        try {
            car.setInteriorColor(doc.select("div:nth-child(1) > div:nth-child(3) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("icolor set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseMileage(Document doc, Car car) throws InvalidFormatException {
        car.setMileage(null);
        try {
            car.setMileage(Integer.parseInt(doc.select("div:nth-child(1) > div:nth-child(4) > h4:nth-child(2) > span:nth-child(2)").text().trim().replaceAll(",","")));
            logger.info("mileage set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseCtyMPG(Document doc, Car car) throws InvalidFormatException {
        car.setCtyMpg(null);
        try {
            String s=doc.select("div:nth-child(1) > div:nth-child(5) > h4:nth-child(2) > span:nth-child(2)").text().trim().substring(0,2);
            if(s.equals("nu"))
                s="0";
            //car.setCtyMpg(Integer.parseInt(doc.select("div:nth-child(1) > div:nth-child(5) > h4:nth-child(2) > span:nth-child(2)").text().trim().substring(0,2)));
            car.setCtyMpg(Integer.parseInt(s));
            logger.info("cmpg set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseHwyMPG(Document doc, Car car) throws InvalidFormatException {
        car.setHwyMpg(null);
        try {
            String s=doc.select("div:nth-child(1) > div:nth-child(5) > h4:nth-child(2) > span:nth-child(2)").text().trim().substring(9,11);
            logger.info(s);
            if(s.equals("/ "))
                s="0";
            //car.setHwyMpg(Integer.parseInt(doc.select("div:nth-child(1) > div:nth-child(5) > h4:nth-child(2) > span:nth-child(2)").text().trim().substring(9,11)));
            car.setHwyMpg(Integer.parseInt(s));
            logger.info("hmpg set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseTransmission(Document doc, Car car) throws InvalidFormatException {
        car.setTransmission(null);
        try {
            car.setTransmission(doc.select("div:nth-child(2) > div:nth-child(1) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("transmission set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseEngine(Document doc, Car car) throws InvalidFormatException {
        car.setEngine(null);
        try {
            car.setEngine(doc.select("div:nth-child(2) > div:nth-child(2) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("engine set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseDriveType(Document doc, Car car) throws InvalidFormatException {
        car.setDriveType(null);
        try {
            car.setDriveType(doc.select("div:nth-child(2) > div:nth-child(3) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("drivetype set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseFuelType(Document doc, Car car) throws InvalidFormatException {
        car.setFuelType(null);
        try {
            car.setFuelType(doc.select("div:nth-child(2) > div:nth-child(4) > h4:nth-child(2) > span:nth-child(2)").text().trim());
            logger.info("fueltype set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseIncludedFeatures(Document doc, Car car) throws InvalidFormatException {
        car.setIncludedFeatures(null);
        try {
            car.setIncludedFeatures(doc.select(".read-more-body > p:nth-child(1)").text().trim());
            logger.info("included features set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }
    private void parseImage(Document doc, Car car) throws InvalidFormatException {
        car.setImage(null);
        try {
            car.setImage(new Image(doc.select(".image-block").attr("src")));
            logger.info("image set");

        }catch (Exception e) {
            throw new InvalidFormatException("Malformed document");
        }
    }



}
