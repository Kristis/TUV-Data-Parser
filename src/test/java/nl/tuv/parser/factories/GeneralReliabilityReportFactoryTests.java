package nl.tuv.parser.factories;

import nl.tuv.parser.domain.GeneralReliabilityReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class GeneralReliabilityReportFactoryTests {

    private static final String CAR_REPORT_FILE_NAME = "factory/car-report-1.html";

    @Test
    public void shouldReturnNull() {
        assertNull(GeneralReliabilityReportFactory.createGeneralReliabilityReport(null));
    }

    @Test
    public void shouldCreateOneGeneralReport() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Elements recordRows = carReport.getElementsByTag("td");
        GeneralReliabilityReport report = GeneralReliabilityReportFactory.createGeneralReliabilityReport(recordRows.get(8));
        assertNotNull(report);
        assertEquals(report.getUrl(), "/index.php/tuv-report-year-age/2014-2-3/491");
        assertEquals(report.getMinCarAge().intValue(), 2);
        assertEquals(report.getMaxCarAge().intValue(), 3);
        assertEquals(report.getVintage().intValue(), 2010);
        assertEquals(report.getAmountOfModels().intValue(), 129);
        assertEquals(report.getAverageFaultRate(), new Double(9.1));
    }

    @Test
    public void shouldCreateListOfGeneralReports() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        List<GeneralReliabilityReport> generalReports = GeneralReliabilityReportFactory.createGeneralReliabilityReports(carReport);
        assertNotNull(generalReports);
        assertEquals(generalReports.size(), 60);
    }

    @Test
    public void shouldCreateMapOfGeneralReports() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(CAR_REPORT_FILE_NAME).getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Map<String, GeneralReliabilityReport> generalReports = GeneralReliabilityReportFactory.createGeneralReliabilityReportMap(carReport);
        assertNotNull(generalReports);
        assertEquals(generalReports.size(), 60);
    }
}
