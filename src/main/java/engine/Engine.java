package main.java.engine;

import main.java.input.Scanner;
import main.java.output.DisplayLcd;
import main.java.output.Printer;
import main.java.product.Product;

import java.util.Map;

public class Engine {

    private Map<String, Product> ListOfProducts;
    private Printer printer;
    private Scanner scanner;
    private DisplayLcd display;

    private String enteredBarcode;

    public Engine() {

    }
    public void startEngine(String enteredBarcode){
        this.enteredBarcode = enteredBarcode;
        scanner.startScan(this.enteredBarcode);
    }

    private void productFound() {
        addProduct(ListOfProducts.get(enteredBarcode));
    }
    private void addProduct(Product product) {
        display.scanProduct(product);
        printer.scanProduct(product);
    }
    private void endTransaction() {
        printer.endTransaction();
        System.exit(0);
    }
    private void setCommandConditions(Map<EnumEngine, Runnable> commandMap) {
        commandMap.put(EnumEngine.PRODUCT_FOUND, this::productFound);
        commandMap.put(EnumEngine.EXIT_CODE, this::endTransaction)
        commandMap.put(EnumEngine.INVALID_BARCODE, () -> display.showInvalidBarcode());
        commandMap.put(EnumEngine.PRODUCT_NOT_FOUND, () -> display.showProductNotFound());
       ;
    }
}
