package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.Manufacturer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class ManufacturerFactory {


    public static List<Manufacturer> createManufactures(Document manufacturesDocument) {
        List<Element> manufactures = manufacturesDocument.getElementsByTag(ParsingConstants.SPAN_TAG);
        List<Manufacturer> manufacturesEntities = new ArrayList<>();
        manufactures.forEach(element -> {
            manufacturesEntities.add(createManufacturer(element));
        });
        return manufacturesEntities;
    }

    public static Manufacturer createManufacturer(Element element) {
        Manufacturer m = new Manufacturer();
        if (element.children() != null) {
            m.setName(element.children().text());
            m.setUrl(element.children().attr(ParsingConstants.ATTR_HREF));
        }
        return m;
    }
}
