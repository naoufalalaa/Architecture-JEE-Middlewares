package ma.enset.ecom;

import ma.enset.ecom.entities.Category;
import ma.enset.ecom.entities.Product;
import ma.enset.ecom.repositories.CategoryRepository;
import ma.enset.ecom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            Stream.of("Electronics","Food","Clothes").forEach(name -> {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            });
            categoryRepository.findAll().forEach(category -> {
                Product product = new Product();
                product.setId(UUID.randomUUID().toString());
                product.setName("Product " + category.getName());
                product.setPrice(Math.random() * 9000);
                product.setQuantity(Math.random() * 1000);
                product.setCategory(category);
                productRepository.save(product);
            });
        };
    }
    /*
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Stream.of("Computer","Milk","Bread","Cake").forEach(name -> {
                productRepository.save(new Product(UUID.randomUUID().toString(),name,Math.random()*10000,Math.random()*100));
            });
        };
    }
     */
}
