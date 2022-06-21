package lt.bit.products.store.service;

import lt.bit.products.store.model.Product;
import lt.bit.products.store.model.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface ProductItemsRepository extends JpaRepository<ProductItems, Integer> {
    void deleteAllByProductId (Integer productId);

}
