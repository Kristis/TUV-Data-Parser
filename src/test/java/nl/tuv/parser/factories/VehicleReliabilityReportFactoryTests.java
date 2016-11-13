package nl.tuv.parser.factories;

import nl.tuv.parser.domain.GeneralReliabilityReport;
import nl.tuv.parser.domain.Vehicle;
import nl.tuv.parser.domain.VehicleReliabilityReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kristisvaskys on 13/11/2016.
 */
public class VehicleReliabilityReportFactoryTests {

    private static final String CAR_REPORT_FILE_NAME = "factory/car-report-1.html";

    @Test
    public void shouldCreateOneReliabilityReport() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Elements recordRows = carReport.getElementsByTag("td");
        VehicleReliabilityReport vehicleReport = VehicleReliabilityReportFactory.createVehicleReliabilityReport(recordRows.get(81));
        assertNotNull(vehicleReport);
        assertEquals(vehicleReport.getFaultRate(), new Double(5.5));
        assertEquals(vehicleReport.getRank().intValue(), 35);
        assertEquals(vehicleReport.getPercentage().intValue(), 68);
        assertEquals(vehicleReport.getPercentageOut().intValue(), 32);


    }

    @Test
    public void shouldCreateOneReliabilityReportWithVehicle() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Elements recordRows = carReport.getElementsByTag("td");
        Vehicle vehicle = new Vehicle();
        vehicle.setModelName("Mazda 626");
        VehicleReliabilityReport vehicleReport = VehicleReliabilityReportFactory.createVehicleReliabilityReport(recordRows.get(81), vehicle);
        assertNotNull(vehicleReport);
        assertNotNull(vehicleReport.getVehicle());
        assertEquals(vehicleReport.getVehicle().getModelName(), vehicle.getModelName());
        assertEquals(vehicleReport.getFaultRate(), new Double(5.5));
        assertEquals(vehicleReport.getRank().intValue(), 35);
        assertEquals(vehicleReport.getPercentage().intValue(), 68);
        assertEquals(vehicleReport.getPercentageOut().intValue(), 32);


    }

    @Test
    public void shouldCreateSixteenReliabilityReportsVehicleNull() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        List<VehicleReliabilityReport> reports = VehicleReliabilityReportFactory.createVehicleReliabilityReports(carReport, null);
        assertNotNull(reports);
        assertEquals(reports.size(), 32);
    }

    @Test
    public void shouldVehicleReportsWithGeneralReportRelationship() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Map<String, GeneralReliabilityReport> generalReports = GeneralReliabilityReportFactory.createGeneralReliabilityReportMap(carReport);
        assertNotNull(generalReports);
        assertEquals(generalReports.size(), 60);

        Vehicle vehicle = new Vehicle();
        vehicle.setModelName("Mazda 626");
        List<VehicleReliabilityReport> reports = VehicleReliabilityReportFactory.createVehicleReliabilityReports(carReport, vehicle, generalReports);
        assertNotNull(reports);
        assertEquals(reports.size(), 32);
        assertNotNull(reports.get(0).getGeneralReliabilityReport());
    }
}
