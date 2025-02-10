package com.example.demo.category;

import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            CategoryRepository repository){
        return args -> {
            Category house = new Category(
                    "House"
            );
            Category flat = new Category(
                    "Kvartira"
            );
            Category dacha = new Category(
                    "Dacha"
            );

            repository.saveAll(
                    List.of(house, flat, dacha)
            );

        };
    }

}
