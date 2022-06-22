package lt.bit.products.store.jobs;

import lt.bit.products.store.model.Product;
import lt.bit.products.store.service.ProductItemsRepository;
import lt.bit.products.store.service.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class Production {
    private final static Logger LOG = LoggerFactory.getLogger(Production.class);

@Value("${jobs.production.number_of_new_products}")
private int numberOfNewProducts;

    private final ProductRepository productRepository;
    private final ProductItemsRepository productItemsRepository;

    Production(ProductRepository productRepository, ProductItemsRepository productItemsRepository) {
        this.productRepository = productRepository;
        this.productItemsRepository = productItemsRepository;
    }


    @Scheduled(fixedRateString = "20000")
    void addNewProducts() {
        LOG.info("Production job started");
        LOG.info("Adding product...");



        for (int i=0; i<numberOfNewProducts; i++) {
            Product generateProduct = createProduct(i+1);
            productRepository.save(generateProduct);
            LOG.info("i=" +i + " ->" + generateProduct + " - SAVED!");
        } LOG.info("Generated price: {}", generatePrice());

    }

    private Product createProduct(int i) {
        Product product = new Product();
        product.setName(generateName(i));
        product.setDescription(generateDescription());
        product.setCreated(LocalDate.now());

        return product;
    }

    private String generateName(int i){
        LocalDate date = LocalDate.now();
        String text = date.format(DateTimeFormatter.ofPattern("E"));
        String name = "";
        name = "" + text + "-" + (i+1);
        return name;
    }
    private String generateName2(int index) {
        String dayOfWeek = DateTimeFormatter.ofPattern("E", Locale.ENGLISH).format(LocalDateTime.now());
        return String.format("%s-%d", dayOfWeek, index);
    }


    private String generateDescription (){
    return String.format("Desc + "+ LocalDate.now());
    }

    private BigDecimal generatePrice() {
        return new BigDecimal(LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm.ss")));

//        LocalDateTime time = LocalDateTime.now();
//        int hr = time.getHour();
//        int min = time.getMinute();
//        int sec = time.getSecond();
//        return new BigDecimal(String.format("%d.%d", min, sec));

    }

}
