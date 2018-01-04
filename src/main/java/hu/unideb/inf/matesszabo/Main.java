package hu.unideb.inf.matesszabo;

import hu.unideb.inf.matesszabo.api.*;
import hu.unideb.inf.matesszabo.utils.IMRouter;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class Main extends Application {
    static {
        System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
    }

    private static final String PATH = "http://localhost";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        Server server = new Server(Protocol.HTTP, 8888, new Main());
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restlet createInboundRoot() {
        IMRouter router = new IMRouter(getContext(), PATH, PORT);
        router.attach("/car?id={id}", CarByLink.class);
        //router.attach("/carList?size={size}&zip={zip}&make={make}&bodystyle={bodystyle}&minprice={minprice}&maxprice={maxprice}&minyear={minyear}&maxyear={maxyear}&minmileage={minmileage}&maxmileage={maxmileage}&color={color}&pricerating={pricerating}&drivetype={drivetype}&fueltype={fueltype}&transmission={transmission}&engine={engine}", CarList.class);
        router.attach("/carList?make={make}", CarList.class);

        return router.getRouter();
    }


}
