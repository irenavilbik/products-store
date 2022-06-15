package lt.bit.products.store.controller;

import lt.bit.products.store.model.Product;
import lt.bit.products.store.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({ProductController.ROOT_MAPPING})

class ProductController {
    public static final String ROOT_MAPPING = "/products";
    private final ProductService service;

    ProductController (ProductService service){
        this.service= service;
    }
    @GetMapping
    List<Product> fetchProducts() {
        return service.findProducts();
    }
    @GetMapping("/{id}")
    ResponseEntity<Product> fetchProducts(@PathVariable Integer id) {

        Product product = service.findProduct(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId) {
        service.deleteProduct(productId);

    }


}
