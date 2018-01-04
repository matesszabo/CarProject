package hu.unideb.inf.matesszabo.api;

import hu.unideb.inf.matesszabo.model.Car;
import hu.unideb.inf.matesszabo.services.CarByLinkService;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;

/**
 * Created by mates on 2018. 01. 02..
 */
public class CarByLink extends ServerResource {
    private final static String REMOTE_ITEM_URI = "/listing";

    @Get("json|xml")
    public Car represent() {
        String id = getAttribute("id");

        CarByLinkService carByLinkService = new CarByLinkService();

        try {
            return carByLinkService.doSearch(REMOTE_ITEM_URI+"/"+id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);

    }
}
