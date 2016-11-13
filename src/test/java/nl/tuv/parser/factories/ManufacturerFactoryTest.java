package nl.tuv.parser.factories;

import nl.tuv.parser.constants.ParsingConstants;
import nl.tuv.parser.domain.Manufacturer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kristisvaskys on 12/11/2016.
 */
public class ManufacturerFactoryTest {

    private static final String MANUFACTURES_FILE_NAME = "factory/manufactures.html";

    @Test
    public void shouldCreateOneManufacture() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(MANUFACTURES_FILE_NAME).getFile());
        Document manufaturesDocument = Jsoup.parse(file, "UTF-8");
        List<Element> manufaturesElements = manufaturesDocument.getElementsByTag(ParsingConstants.SPAN_TAG);
        Manufacturer manufacture = ManufacturerFactory.createManufacturer(manufaturesElements.get(0));
        assertNotNull(manufacture);
        assertNotNull(manufacture.getName());
        assertEquals(manufacture.getName(), "Alfa Romeo");
    }

    @Test
    public void shouldCreateListOfManufactures() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(MANUFACTURES_FILE_NAME).getFile());
        Document manufaturesDocument = Jsoup.parse(file, "UTF-8");
        List<Manufacturer> manufatures = ManufacturerFactory.createManufactures(manufaturesDocument);
        assertNotNull(manufatures);
        assertNotNull(manufatures.get(0).getName());
        assertEquals(manufatures.get(0).getName(), "Alfa Romeo");
        assertEquals(manufatures.size(), 35);
    }

}
