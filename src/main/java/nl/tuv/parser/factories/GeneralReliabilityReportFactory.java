package nl.tuv.parser.factories;

import nl.tuv.parser.domain.GeneralReliabilityReport;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class GeneralReliabilityReportFactory {

    public static final List<GeneralReliabilityReport> createGeneralReliabilityReports(Document carReports){
        List<GeneralReliabilityReport> generalReports = new ArrayList<>();
        Elements recordRows = carReports.getElementsByTag("td");
        recordRows.forEach(element -> {generalReports.add(createGeneralReliabilityReport(element));});
        return generalReports;
    }

    public static GeneralReliabilityReport createGeneralReliabilityReport(Element record){
        String generalRecordUrl = record.children().attr("href");
        if(generalRecordUrl != null && generalRecordUrl.length() > 0 && generalRecordUrl.contains("report-year-age")) {
            GeneralReliabilityReport generalReliabilityReport = new GeneralReliabilityReport();
            generalReliabilityReport.setUrl(generalRecordUrl);
            if(record.children() != null) {
                String modelYears = record.children().text();
                setYears(modelYears, generalReliabilityReport);
            }

            if(record.textNodes() != null && record.textNodes().get(0) != null){
                String faultRate = record.textNodes().get(0).text();
                setFaultRate(faultRate, generalReliabilityReport);
            }
            return generalReliabilityReport;
        }

        return null;
    }

    private static void setYears(String years, GeneralReliabilityReport report){
        if(years != null && years != "") {
            String[] y = years.split("-");
            report.setReportYear(Integer.valueOf(y[0].trim()));
            report.setMinCarAge(Integer.valueOf(y[1].trim()));
            report.setMaxCarAge(Integer.valueOf(y[2].split(" ")[0].trim()));
            report.setVintage(Integer.valueOf(y[3].substring(2, 6).trim()));
        }
    }

    private static void setFaultRate(String faultRate, GeneralReliabilityReport reliabilityReport){
        if(faultRate != null && faultRate != "") {
            String[] f = faultRate.split("--");
            reliabilityReport.setAverageFaultRate(Double.valueOf(f[0].trim().split("%")[0].trim()));
            reliabilityReport.setAmountOfModels(Integer.valueOf(f[1].trim().split(" ")[0]));
        }
    }


}
