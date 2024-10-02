package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository repository){
        return args -> {
            Book life_ex = new Book(
                    "Жизненый опыт",
                    91,
                    400,
                    "Baizak.N, Begaim.A"
            );

            Book gordosty = new Book(
                    "Гордость и предубеждение",
                    382,
                    350,
                    "Jane Osten"
            );

            repository.saveAll(
                    List.of(life_ex, gordosty)
            );

        };
    }

}
