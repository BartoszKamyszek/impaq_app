package main.java.engine;

import main.java.input.Scanner;
import main.java.output.DisplayLcd;
import main.java.output.Printer;
import main.java.product.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class Engine {

    private Map<String, Product> listOfProducts;
    private Printer printer;
    private Scanner scanner;
    private DisplayLcd displayLcd;

    private String enteredBarcode;

    public Engine() throws IOException, SAXException, ParserConfigurationException {

        XmlParser xmlParser = new XmlParser();
        listOfProducts = xmlParser.getAllProducts();

        Map<EnumEngine, Runnable> commandMap = new EnumMap<>(EnumEngine.class);
        setCommandConditions(commandMap);

        displayLcd = new DisplayLcd();
        printer = new Printer();
        scanner = new Scanner(commandMap, listOfProducts);

    }
    public void startEngine(String enteredBarcode){
        this.enteredBarcode = enteredBarcode;
        scanner.startScan(this.enteredBarcode);
    }

    private void productFound() {
        addProduct(listOfProducts.get(enteredBarcode));
    }
    private void addProduct(Product product) {
        displayLcd.scanProduct(product);
        printer.scanProduct(product);
    }
    private void endTransaction() {
        printer.endTransaction();
        System.exit(0);
    }
    private void setCommandConditions(Map<EnumEngine, Runnable> commandMap) {
        commandMap.put(EnumEngine.PRODUCT_FOUND, this::productFound);
        commandMap.put(EnumEngine.EXIT_CODE, this::endTransaction);
        commandMap.put(EnumEngine.INVALID_BARCODE, () -> displayLcd.showInvalidBarcode());
        commandMap.put(EnumEngine.PRODUCT_NOT_FOUND, () -> displayLcd.showProductNotFound());
       ;
    }
}
