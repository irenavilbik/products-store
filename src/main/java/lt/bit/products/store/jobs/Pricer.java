package lt.bit.products.store.jobs;

import lt.bit.products.store.model.ProductItems;
import lt.bit.products.store.service.ProductItemsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
@Component
public class Pricer {

    private final static Logger LOG = (Logger) LoggerFactory.getLogger(Pricer.class);
    private final ProductItemsRepository itemsRepository;

  Pricer(ProductItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Scheduled(fixedRateString = "15000")
    void recalculateProductPrice() {
        LOG.info("Pricer job started");
        LOG.info("Recalculating product prices...");

        Random random = new Random();
        List<ProductItems> items = itemsRepository.findAll();

    for(ProductItems item:items){
        BigDecimal oldPrice = item.getPrice();
        BigDecimal randomVal = BigDecimal.valueOf(random.nextDouble()+1);
        LocalDateTime now = LocalDateTime.now();
        BigDecimal newPrice = (now.getSecond() % 2 == 0)
                ? oldPrice.multiply(randomVal)
                : oldPrice.divide(randomVal, RoundingMode.UP);

        if(now.getHour() > 20){
            newPrice = newPrice.multiply(BigDecimal.valueOf(0.8));
        }

    LOG.info(String.format("ID: %d OLD PRICE: %.2f NEW PRICE :  n/a" ,
            item.getProductId(), oldPrice, newPrice));
}

        LOG.info("Product prices updated.");
    }
}
