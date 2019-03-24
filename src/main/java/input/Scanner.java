package main.java.input;

import main.java.engine.EnumEngine;
import main.java.product.Product;
import java.util.Map;

public class Scanner {

    private Map<String, Product> listOfProducts;
    private Map<EnumEngine, Runnable> commandMap;

    public Scanner(Map<EnumEngine, Runnable> commandMap, Map<String, Product> allProducts){
        this.listOfProducts = allProducts;
        this.commandMap = commandMap;
    }

    public void startScan(String enteredBarcode){
        scan(enteredBarcode, commandMap, listOfProducts);
    }

    private void scan(String enteredBarcode, Map<EnumEngine, Runnable> commandMap, Map<String, Product> listOfProducts) {
        if (barcodeValidation(enteredBarcode) == EnumEngine.INVALID_BARCODE)
            commandMap.get(EnumEngine.INVALID_BARCODE).run();
        else if (barcodeValidation(enteredBarcode) == EnumEngine.VALID_BARCODE)
            searchProduct(enteredBarcode, commandMap, listOfProducts);
        else
            commandMap.get(EnumEngine.EXIT_CODE).run();
    }

    private void searchProduct(String enteredBarcode, Map<EnumEngine, Runnable> commandMap, Map<String, Product> listOfProducts) {
        if (listOfProducts.get(enteredBarcode) != null)
            commandMap.get(EnumEngine.PRODUCT_FOUND).run();
        else
            commandMap.get(EnumEngine.PRODUCT_NOT_FOUND).run();
    }


    private EnumEngine barcodeValidation(String barcode) {
        if (barcode.matches("[0-9]+") && barcode.length() == 3 )
            return EnumEngine.VALID_BARCODE;
        else if (barcode.toUpperCase().matches("EXIT"))
            return EnumEngine.EXIT_CODE;
        else
            return EnumEngine.INVALID_BARCODE;
    }

}
