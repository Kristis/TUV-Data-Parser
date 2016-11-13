package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.GeneralReliabilityReport;
import nl.tuv.parser.factories.factoryUtils.GeneralReliabilityReportUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class GeneralReliabilityReportFactory {

    public static Map<String, GeneralReliabilityReport> createGeneralReliabilityReportMap(Document carReports) {
        Map<String, GeneralReliabilityReport> reportMap = new HashMap<>();
        Elements recordRows = carReports.getElementsByTag(ParsingConstants.TABLE_TD);
        recordRows.forEach(element -> {
            GeneralReliabilityReport report = createGeneralReliabilityReport(element);
            if (report != null) {
                reportMap.put(report.getUrl(), report);
            }
        });
        return reportMap;
    }

    public static List<GeneralReliabilityReport> createGeneralReliabilityReports(Document carReports) {
        List<GeneralReliabilityReport> generalReports = new ArrayList<>();
        Elements recordRows = carReports.getElementsByTag(ParsingConstants.TABLE_TD);
        recordRows.forEach(element -> {
            GeneralReliabilityReport report = createGeneralReliabilityReport(element);
            if (report != null) {
                generalReports.add(report);
            }
        });
        return generalReports;
    }

    public static GeneralReliabilityReport createGeneralReliabilityReport(Element record) {
        if (record == null) {
            return null;
        }
        String generalRecordUrl = record.children().attr(ParsingConstants.ATTR_HREF);
        if (GeneralReliabilityReportUtils.isUrlValid(generalRecordUrl)) {
            GeneralReliabilityReport generalReliabilityReport = new GeneralReliabilityReport();
            generalReliabilityReport.setUrl(generalRecordUrl);
            if (record.children() != null) {
                String modelYears = record.children().text();
                setYears(modelYears, generalReliabilityReport);
            }

            if (record.textNodes() != null && record.textNodes().get(0) != null) {
                String faultRate = record.textNodes().get(0).text();
                setFaultRate(faultRate, generalReliabilityReport);
            }
            return generalReliabilityReport;
        }

        return null;
    }

    private static void setYears(String years, GeneralReliabilityReport report) {
        if (years != null && years != "") {
            String[] y = years.split("-");
            report.setReportYear(Integer.valueOf(y[0].trim()));
            report.setMinCarAge(Integer.valueOf(y[1].trim()));
            report.setMaxCarAge(Integer.valueOf(y[2].split(" ")[0].trim()));
            report.setVintage(Integer.valueOf(y[3].substring(2, 6).trim()));
        }
    }

    private static void setFaultRate(String faultRate, GeneralReliabilityReport reliabilityReport) {
        if (faultRate != null && faultRate != "") {
            String[] f = faultRate.split("--");
            reliabilityReport.setAverageFaultRate(Double.valueOf(f[0].trim().split("%")[0].trim()));
            reliabilityReport.setAmountOfModels(Integer.valueOf(f[1].trim().split(" ")[0]));
        }
    }


}
