package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.Vehicle;
import nl.tuv.parser.domain.Manufacturer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class VehicleFactory {



    public static List<Vehicle> createVehicles(Manufacturer manufacturer, Document carModels){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Elements modelsElements = carModels.getElementsByAttribute(ParsingConstants.ATTR_HREF);
        modelsElements.forEach(element -> {vehicles.add(createVehicle(element, manufacturer));});
        return vehicles;
    }


    public static Vehicle createVehicle(Element element, Manufacturer manufacturer) {
        String modelName = element.text();
        if(modelName.toLowerCase().contains(manufacturer.getName().toLowerCase())) {
            Vehicle vehicle = new Vehicle();
            vehicle.setManufacturer(manufacturer);
            vehicle.setModelName(modelName);
            vehicle.setUrl(element.attr(ParsingConstants.ATTR_HREF));
            return vehicle;
        } else {
            return null;
        }

    }
}
