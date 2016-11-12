package nl.tuv.parser.factories;

import nl.tuv.parser.domain.GeneralReliabilityReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class GeneralReliabilityReportFactoryTests {

    private static final String CAR_REPORT_FILE_NAME = "car-report-1.html";

    @Test
    public void shouldCreateGeneralReports() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("factory/car-report-1.html").getFile());
        Document carReport = Jsoup.parse(file, "UTF-8");
        Elements recordRows = carReport.getElementsByTag("td");
        GeneralReliabilityReport report = GeneralReliabilityReportFactory.createGeneralReliabilityReport(recordRows.get(8));
        assertNotNull(report);
        assertEquals(report.getUrl(), "/index.php/tuv-report-year-age/2014-2-3/491");
        assertEquals(report.getMinCarAge().intValue(),2);
        assertEquals(report.getMaxCarAge().intValue(),3);
        assertEquals(report.getVintage().intValue(), 2010);
        assertEquals(report.getAmountOfModels().intValue(), 129);
    }
}
