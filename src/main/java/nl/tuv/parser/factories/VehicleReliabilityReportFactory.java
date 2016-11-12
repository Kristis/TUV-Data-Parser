package nl.tuv.parser.factories;

import nl.tuv.parser.domain.VehicleReliabilityReport;
import org.jsoup.nodes.Element;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class VehicleReliabilityReportFactory {


    public static VehicleReliabilityReport createVehicleReliabilityReport(Element element){
        VehicleReliabilityReport report = new VehicleReliabilityReport();
        return report;
    }
}
