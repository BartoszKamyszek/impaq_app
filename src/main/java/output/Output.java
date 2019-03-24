package main.java.output;

import main.java.product.Product;

public abstract class Output {

    protected static String PRODUCT_FOUND_MESSAGE = "PRODUCT FOUND";
    protected static String TRANSACTION_FINISHED_MESSAGE = "TRANSACTION FINISHED";
    protected static String TOTAL_PRICE_MESSAGE = "TOTAL:";

    protected static String INVALID_BARCODE_MESSAGE = "INVALID BARCODE";
    protected static String PRODUCT_NOT_FOUND_MESSAGE = "PRODUCT NOT FOUND";
    protected static String PRINT_MESSAGE = "PRINTING...";

    public abstract void scanProduct(Product product);

    public void productDetails(Product product) {
        System.out.println("Barcode: " + product.getBarcode() + "\n" +
        "Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n");
    }

}
