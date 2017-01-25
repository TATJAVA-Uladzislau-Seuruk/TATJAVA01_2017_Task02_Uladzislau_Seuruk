package com.epam.oop;

import com.epam.oop.shop.SportEquipment;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses data with shop's assortment.
 *
 * @author Uladzislau Seuruk.
 */
public class ItemsParser {
    /**
     * Constant with default file name.
     */
    private static final String FILENAME = "equipment.xml";
    /**
     * Constant with default tag name.
     */
    private static final String TAG_NAME = "item";
    /**
     * File to read data from.
     */
    private File file;

    /**
     * If received <tt>File</tt> wasn't initialized creates new with default path to file with
     * data.
     *
     * @param file <tt>File</tt> to read data from.
     */
    public ItemsParser(File file) {
        if (file == null) {
            this.file = new File(System.getProperty(("user.dir")) + File.separator
                    + FILENAME);
        } else {
            this.file = file;
        }
    }

    /**
     * Parses file with data about shop's assortment.
     *
     * @return <tt>HashMap</tt> with data about shop's assortment.
     */
    public HashMap<SportEquipment, Integer> getItems() {
        if (!file.exists()) {
            return null;
        }
        HashMap<SportEquipment, Integer> items = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList tagList = document.getElementsByTagName(TAG_NAME);
            for (int i = 0; i < tagList.getLength(); ++i) {
                parseItemAndAddToMap(tagList.item(i), items);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    /**
     * Parses received xml tag and, if parsed values are acceptable, adds them to received map.
     *
     * @param tag <tt>Node</tt> to parse.
     * @param itemMap <tt>Map</tt> to add values to.
     */
    private void parseItemAndAddToMap(Node tag, Map<SportEquipment, Integer> itemMap) {
        NamedNodeMap map = tag.getAttributes();
        Node category = map.getNamedItem("category");
        Node title = map.getNamedItem("title");
        Node price = map.getNamedItem("price");
        try {
            SportEquipment item = new SportEquipment(
                    category == null ? null : category.getNodeValue(),
                    title == null ? null : title.getNodeValue(),
                    price == null ? 0 : Integer.parseInt(price.getNodeValue()));
            itemMap.put(item, Integer.parseInt(tag.getTextContent()));
        } catch (NumberFormatException nfe) {
            System.out.println("Parsing error: Can't convert string \"" + tag.getTextContent()
                    + "\" to integer.\n");
        }
    }
}