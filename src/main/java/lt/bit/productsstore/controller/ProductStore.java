package lt.bit.productsstore.controller;

import lt.bit.productsstore.model.Product;

import java.time.LocalDate;
import java.util.List;

public class ProductStore {
    private static final List<Product> PRODUCTS = List.of{
        new Product (1, "product1", "Desc1", LocalDate.now());
        new Product (1, "product2", "Desc2", LocalDate.now());
        new Product (1, "product3", "Desc3", LocalDate.now());
        new Product (1, "product4", "Desc4", LocalDate.now());

    static List<Product> getProducts() {
        return PRODUCTS;
    }
}
