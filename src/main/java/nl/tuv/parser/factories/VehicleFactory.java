package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.Manufacturer;
import nl.tuv.parser.domain.Vehicle;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class VehicleFactory {


    public static List<Vehicle> createVehicles(Manufacturer manufacturer, Document carModels) {
        List<Vehicle> vehicles = new ArrayList<>();
        Elements modelsElements = carModels.getElementsByAttribute(ParsingConstants.ATTR_HREF);
        modelsElements.forEach(element -> {
            Vehicle v = createVehicle(element, manufacturer);
            if (v != null) {
                vehicles.add(v);
            }
        });
        return vehicles;
    }


    public static Vehicle createVehicle(Element element, Manufacturer manufacturer) {
        String modelName = element.text();
        if (modelName.toLowerCase().contains(manufacturer.getName().toLowerCase()) && !modelName.contains("•")) {
            Vehicle vehicle = createVehicle(element);
            vehicle.setManufacturer(manufacturer);
            return vehicle;
        } else {
            return null;
        }
    }

    public static Vehicle createVehicle(Element element) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModelName(element.text().trim());
        vehicle.setUrl(element.attr(ParsingConstants.ATTR_HREF));
        return vehicle;
    }
}
