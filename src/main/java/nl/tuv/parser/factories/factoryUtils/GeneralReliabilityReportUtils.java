package nl.tuv.parser.factories.factoryUtils;

/**
 * Created by kristisvaskys on 13/11/2016.
 */
public class GeneralReliabilityReportUtils {

    private static final String URL_PREFIX = "tuv-report-year-age";

    public static boolean isUrlValid(String url) {
        return url != null && url.length() > 0 && url.contains(URL_PREFIX);
    }
}
