package main.java.engine;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import main.java.product.Product;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class XmlParser {

    private static final String NODE_NAME = "product";
    private static final String NODE_ATTRIBUTE = "barcode";
    private static final String NODE_NAME_TAG = "productName";
    private static final String NODE_PRICE_TAG = "productPrice";
    private static final String XML_PATH = "src/main/java/database/database.xml";
    private HashMap<String, Product> listOfProducts;

    private File xmlFile = Paths.get(".", XML_PATH).normalize().toFile();
    private DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    private Document document = documentBuilder.parse(xmlFile);
    private NodeList nodeList = document.getElementsByTagName(NODE_NAME);

    public XmlParser() throws ParserConfigurationException, IOException, SAXException {
        this.document.getDocumentElement().normalize();
        this.listOfProducts = parsing(nodeList);
    }

    private HashMap<String, Product> parsing(NodeList nodeList) {
        HashMap<String, Product> products = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Product product = new Product();
                product.setBarcode(element.getAttribute(NODE_ATTRIBUTE));
                product.setName(element.getElementsByTagName(NODE_NAME_TAG).item(0).getTextContent());
                product.setPrice(Integer.parseInt(element.getElementsByTagName(NODE_PRICE_TAG).item(0).getTextContent()));
                products.put(product.getBarcode(), product);
            }
        }
        return products;
    }
    public Map<String, Product> getAllProducts() {
        return listOfProducts;
    }


}
