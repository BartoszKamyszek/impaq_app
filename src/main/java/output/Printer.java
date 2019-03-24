package main.java.output;

import main.java.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Printer extends Output{
    private List<Product> scannedProducts = new ArrayList<>();
    private double totalSum = 0;

    private void totalOut() {
        System.out.println(TRANSACTION_FINISHED_MESSAGE);
        for (Product x: scannedProducts) {
            System.out.println("####### PRODUCT #######" );
            productDetails(x);
        }
        printTotalSumOfProducts();
    }

    public void endTransaction() {
        System.out.println(PRINT_MESSAGE);
        totalOut();
    }

    private void printTotalSumOfProducts() {
        System.out.println(TOTAL_PRICE_MESSAGE);
        System.out.println(this.totalSum);
    }

    @Override
    public void scanProduct(Product product) {
        this.scannedProducts.add(product);
        this.totalSum += product.getPrice();
    }
}
