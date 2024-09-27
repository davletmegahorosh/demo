package com.example.demo;

import com.example.demo.book.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping
	public List<Book> hello(){
		return List.of(
				new Book(
						1,
						"1984",
						270,
						300,
						"J.Oruel"
				),
				new Book(
						2,
						"Горжость и предубеждение",
						383,
						400,
						"Jane Osten"
				)
		);
	}



}
