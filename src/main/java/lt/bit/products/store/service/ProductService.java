package lt.bit.products.store.service;
import lt.bit.products.store.model.Product;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service

public class ProductService {

private final ProductRepository repository;


public ProductService (ProductRepository repository){
    this.repository= repository;

}

    public List<Product> findProducts() {
        return repository.findAll();
    }

    public Product findProduct(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
