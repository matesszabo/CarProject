package hu.unideb.inf.matesszabo.services;

import hu.unideb.inf.matesszabo.model.Car;
import hu.unideb.inf.matesszabo.processor.CarProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by mates on 2018. 01. 02..
 */
public class CarByLinkService {
    private static String ITEM_URI = "https://www.truecar.com/used-cars-for-sale";

    public CarByLinkService() {
    }

    public Car doSearch(String href) throws IOException {
        String url = ITEM_URI + href;
        Document doc = Jsoup.connect(url).userAgent("Mozzila").get();
        CarProcessor cp = new CarProcessor();
        return cp.parse(doc, url);
    }
}
