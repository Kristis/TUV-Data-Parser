package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.GeneralReliabilityReport;
import nl.tuv.parser.domain.Vehicle;
import nl.tuv.parser.domain.VehicleReliabilityReport;
import nl.tuv.parser.factories.factoryUtils.GeneralReliabilityReportUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class VehicleReliabilityReportFactory {

    private static final String PERCENTAGE_IMG = "percentage.gif";

    private static final String PERCENTAGE_OUT_IMG = "percentage_out.gif";

    private static final Integer REPORT_CHILD_SIZE = 6;


    public static List<VehicleReliabilityReport> createVehicleReliabilityReports(Document report, Vehicle vehicle, Map<String, GeneralReliabilityReport> gReports) {
        List<VehicleReliabilityReport> vehicleReliabilityReports = new ArrayList<>();
        Elements recordRows = report.getElementsByTag(ParsingConstants.TABLE_TD);

        String currentGReportUrl = null;
        GeneralReliabilityReport gr = null;
        for (Element e : recordRows) {
            currentGReportUrl = e.children().attr(ParsingConstants.ATTR_HREF);
            if (GeneralReliabilityReportUtils.isUrlValid(currentGReportUrl)) {
                gr = gReports.get(currentGReportUrl);
                currentGReportUrl = null;
            } else {
                currentGReportUrl = null;
            }

            if (e.children().size() == REPORT_CHILD_SIZE) {
                VehicleReliabilityReport r = createVehicleReliabilityReport(e, vehicle, gr);
                if (r != null) {
                    vehicleReliabilityReports.add(r);
                }
                gr = null;
            }

        }
        return vehicleReliabilityReports;
    }

    public static List<VehicleReliabilityReport> createVehicleReliabilityReports(Document report, Vehicle vehicle) {
        List<VehicleReliabilityReport> vehicleReliabilityReports = new ArrayList<>();
        Elements recordRows = report.getElementsByTag(ParsingConstants.TABLE_TD);

        recordRows.forEach(element -> {
            if (element.children().size() == REPORT_CHILD_SIZE) {
                VehicleReliabilityReport r = createVehicleReliabilityReport(element, vehicle);
                if (r != null) {
                    vehicleReliabilityReports.add(r);
                }
            }
        });
        return vehicleReliabilityReports;
    }

    public static VehicleReliabilityReport createVehicleReliabilityReport(Element element, Vehicle vehicle, GeneralReliabilityReport generalReliabilityReport) {
        VehicleReliabilityReport report = createVehicleReliabilityReport(element, vehicle);
        report.setGeneralReliabilityReport(generalReliabilityReport);
        return report;
    }


    public static VehicleReliabilityReport createVehicleReliabilityReport(Element element, Vehicle vehicle) {
        VehicleReliabilityReport report = createVehicleReliabilityReport(element);
        report.setVehicle(vehicle);
        return report;
    }

    public static VehicleReliabilityReport createVehicleReliabilityReport(Element element) {
        if (element == null) {
            return null;
        }
        String rank = element.text();
        String faultRate = element.children().text();
        Elements percentagesElements = element.children().tagName("img");
        VehicleReliabilityReport report = new VehicleReliabilityReport();
        report.setFaultRate(Double.valueOf(faultRate.trim().split("%")[0]));
        report.setRank(Integer.valueOf(rank.trim().split(" ")[0].trim().replace(".", "")));
        for (Element e : percentagesElements) {
            String imgSrc = e.attr("src");
            if (imgSrc != null && imgSrc.contains(PERCENTAGE_OUT_IMG)) {
                report.setPercentageOut(Integer.valueOf(e.attr("width")));
            } else if (imgSrc != null && imgSrc.contains(PERCENTAGE_IMG)) {
                report.setPercentage(Integer.valueOf(e.attr("width")));
            }
        }
        return report;
    }
}
