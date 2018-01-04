package hu.unideb.inf.matesszabo.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hu.unideb.inf.matesszabo.model.ResultList;
import hu.unideb.inf.matesszabo.processor.CarListProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by mates on 2018. 01. 03..
 */
public class CarListService {

    private static final String SEARCH_URI = "https://www.truecar.com/used-cars-for-sale/listings/";

    public CarListService() {
    }


    public ResultList doSearch(String size, String zip, String make, String bodyStyle, String minPrice, String maxPrice, String minYear, String maxYear, String minMileage, String maxMileage, String color, String priceRating, String driveType, String fuelType, String transmission, String engine) throws IOException {

        WebClient webClient = new WebClient();
        String uri = SEARCH_URI;
        int tempSize = 50;

        /*if (size != null) {
            tempSize = Integer.valueOf(size);
            uri += "?listlimit=" + tempSize;
        }*/
        if (make != null && !make.isEmpty()){
            uri=uri+make;
        }
        if (zip != null && !zip.isEmpty()){
            uri=uri+zip+"/";
        }
        if (bodyStyle != null && !bodyStyle.isEmpty()){
            uri=uri+bodyStyle+"/";
        }

        if (minPrice != null && !minPrice.isEmpty()){
            uri=uri+minPrice+"/";
        }

        if (maxPrice != null && !maxPrice.isEmpty()){
            uri=uri+maxPrice+"/";
        }

        if (minYear != null && !minYear.isEmpty()){
            uri=uri+minYear+"/";
        }

        if (maxYear != null && !maxYear.isEmpty()){
            uri=uri+maxYear+"/";
        }

        if (minMileage != null && !minMileage.isEmpty()){
            uri=uri+minMileage+"/";
        }

        if (maxMileage != null && !maxMileage.isEmpty()){
            uri=uri+maxMileage+"/";
        }

        if (color != null && !color.isEmpty()){
            uri=uri+color+"/";
        }

        if (priceRating != null && !priceRating.isEmpty()){
            uri=uri+priceRating+"/";
        }

        if (driveType != null && !driveType.isEmpty()){
            uri=uri+driveType+"/";
        }

        if (fuelType != null && !fuelType.isEmpty()){
            uri=uri+fuelType+"/";
        }

        if (transmission != null && !transmission.isEmpty()){
            uri=uri+transmission+"/";
        }

        if (engine != null && !engine.isEmpty()){
            uri=uri+engine+"/";
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   1   !!!!!!!!!!!!!!!!!!!!!!!!"+uri);
        HtmlPage page = webClient.getPage("https://www.truecar.com/used-cars-for-sale/listings/ford/");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   2   !!!!!!!!!!!!!!!!!!!!!!!!");
        String content = page.getWebResponse().getContentAsString();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   3   !!!!!!!!!!!!!!!!!!!!!!!!");
        Document doc = Jsoup.parse(content);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   4   !!!!!!!!!!!!!!!!!!!!!!!!");
        CarListProcessor carListProcessor = new CarListProcessor();
        ResultList resultList = carListProcessor.parse(doc,size);
        resultList.setNumOfItem(tempSize);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!   5   !!!!!!!!!!!!!!!!!!!!!!!!");
        return resultList;
    }
}
