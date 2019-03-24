package main.java.output;

import main.java.product.Product;

public class DisplayLcd extends Output {

    @Override
    public void scanProduct(Product product) {
        System.out.println(PRODUCT_FOUND_MESSAGE);
        productDetails(product);
    }

    public void showInvalidBarcode() {
        System.out.println(INVALID_BARCODE_MESSAGE);
    }

    public void showProductNotFound() {
        System.out.println(PRODUCT_NOT_FOUND_MESSAGE);
    }
}
