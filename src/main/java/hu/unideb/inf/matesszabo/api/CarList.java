package hu.unideb.inf.matesszabo.api;

import hu.unideb.inf.matesszabo.model.ResultList;
import hu.unideb.inf.matesszabo.services.CarListService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Created by mates on 2018. 01. 03..
 */
public class CarList extends ServerResource {

    private final static String REMOTE_ITEM_URI = "/listing";

    @Get("json|xml")
    public ResultList represent() {
        //String size = getAttribute("size");
        //String zip = getAttribute("zip");
        String make = getAttribute("make");
        /*String bodyStyle = getAttribute("bodystyle");
        String minPrice = getAttribute("minprice");
        String maxPrice = getAttribute("maxprice");
        String minYear = getAttribute("minyear");
        String maxYear = getAttribute("maxyear");
        String minMileage = getAttribute("minmileage");
        String maxMileage = getAttribute("maxmileage");
        String color = getAttribute("color");
        String priceRating = getAttribute("pricerating");
        String driveType = getAttribute("drivetype");
        String fuelType = getAttribute("fueltype");
        String transmission = getAttribute("transmission");
        String engine = getAttribute("engine")*/;


        CarListService carListService = new CarListService();

        try {
            //return carListService.doSearch(size,zip,make,bodyStyle,minPrice,maxPrice,minYear,maxYear,minMileage,maxMileage,color,priceRating,driveType,fuelType,transmission,engine);
            return carListService.doSearch(null,null,make,null,null,null,null,null,null,null,null,null,null,null,null,null);
        } catch (Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
        }
    }
}
