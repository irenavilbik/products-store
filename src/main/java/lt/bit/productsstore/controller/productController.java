package lt.bit.productsstore.controller;

import lt.bit.productsstore.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/products")
class ProductController {

    @GetMapping
    List<Product> fetchProducts() {
        return ProductStore.getProducts();
    }
}