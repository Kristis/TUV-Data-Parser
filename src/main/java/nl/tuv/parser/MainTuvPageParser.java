package nl.tuv.parser;

import nl.tuv.parser.domain.GeneralReliabilityReport;
import nl.tuv.parser.domain.Manufacturer;
import nl.tuv.parser.domain.Vehicle;
import nl.tuv.parser.domain.VehicleReliabilityReport;
import nl.tuv.parser.factories.GeneralReliabilityReportFactory;
import nl.tuv.parser.factories.ManufacturerFactory;
import nl.tuv.parser.factories.VehicleFactory;
import nl.tuv.parser.factories.VehicleReliabilityReportFactory;
import nl.tuv.parser.repositories.ManufacturerRepository;
import nl.tuv.parser.repositories.VehicleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
@Component
public class MainTuvPageParser implements CommandLineRunner {

    private static final String INDEX_PAGE_URL = "http://www.anusedcar.com";

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {

        Document indexDocument = Jsoup.connect(INDEX_PAGE_URL).get();
        List<Manufacturer> manufacturesEntities = ManufacturerFactory.createManufactures(indexDocument);
        manufacturerRepository.save(manufacturesEntities);

        List<Vehicle> vehicles = new ArrayList<>();
        for (Manufacturer m : manufacturesEntities) {
            Document carModels = Jsoup.connect(INDEX_PAGE_URL + m.getUrl()).get();
            vehicles.addAll(VehicleFactory.createVehicles(m, carModels));
        }
        vehicleRepository.save(vehicles);

        Map<String, GeneralReliabilityReport> generalReliabilityReports = null;
        Document carRecords = Jsoup.connect(INDEX_PAGE_URL + vehicles.get(0).getUrl()).get();
        generalReliabilityReports = GeneralReliabilityReportFactory.createGeneralReliabilityReportMap(carRecords);
        
        List<VehicleReliabilityReport> allVehicleReports = new ArrayList<>();
        for (Vehicle v : vehicles) {
            Document vehicleReportDoc = Jsoup.connect(INDEX_PAGE_URL + v.getUrl()).get();
            List<VehicleReliabilityReport> vehicleReports = VehicleReliabilityReportFactory.createVehicleReliabilityReports(vehicleReportDoc, v, generalReliabilityReports);
            allVehicleReports.addAll(vehicleReports);
        }

    }


}
