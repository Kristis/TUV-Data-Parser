package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.Manufacturer;
import nl.tuv.parser.domain.Vehicle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kristisvaskys on 13/11/2016.
 */
public class VehiclesFactoryTests {

    private static final String VEHICLES_FILE_NAME = "factory/vehicles.html";

    @Test
    public void shouldCreateOneVehicle() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VEHICLES_FILE_NAME).getFile());
        Document carModels = Jsoup.parse(file, "UTF-8");
        Elements modelsElements = carModels.getElementsByAttribute(ParsingConstants.ATTR_HREF);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Alfa Romeo");
        Vehicle vehicle = VehicleFactory.createVehicle(modelsElements.get(3));
        assertNotNull(vehicle);
        assertEquals(vehicle.getModelName(), "Alfa Romeo 145");
    }

    @Test
    public void shouldCreateOneVehicleWithManufature() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VEHICLES_FILE_NAME).getFile());
        Document carModels = Jsoup.parse(file, "UTF-8");
        Elements modelsElements = carModels.getElementsByAttribute(ParsingConstants.ATTR_HREF);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Alfa Romeo");
        Vehicle vehicle = VehicleFactory.createVehicle(modelsElements.get(3), manufacturer);
        assertNotNull(vehicle);
        assertEquals(vehicle.getManufacturer().getName(), manufacturer.getName());
        assertEquals(vehicle.getModelName(), "Alfa Romeo 145");
    }

    @Test
    public void shouldCreateListOFVehiclesWithManufature() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VEHICLES_FILE_NAME).getFile());
        Document carModels = Jsoup.parse(file, "UTF-8");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Alfa Romeo");
        List<Vehicle> vehicles = VehicleFactory.createVehicles(manufacturer, carModels);
        assertNotNull(vehicles);
        assertEquals(vehicles.size(), 6);
        assertEquals(vehicles.get(0).getManufacturer().getName(), manufacturer.getName());
        assertEquals(vehicles.get(0).getModelName(), "Alfa Romeo 145");
    }
}
