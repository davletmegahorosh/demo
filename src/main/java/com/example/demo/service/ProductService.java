package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.dto.ProductDetailsResponse;
import com.example.demo.dto.ProductListResponse;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductListResponse> productsList() {
        return productRepository.findAll().stream()
                .map(product -> new ProductListResponse(product.getName(), product.getPrice(), product.getImageBase64()))
                .collect(Collectors.toList());
    }

    public ProductDetailsResponse getProductById(Long productId) {
        Product product = getProductOrThrow(productId);
        return new ProductDetailsResponse(
                product.getName(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getAuthor().getFirstname() + " " + product.getAuthor().getLastname(),
                product.getDescription(),
                product.getImageBase64()
        );
    }

    public void addNewProduct(Product product) {
        Optional<Product> productByName = productRepository.findProductByName(product.getName());
        if (productByName.isPresent()) {
            throw new IllegalStateException("Продукт с таким названием уже существует");
        }
        productRepository.save(product);
    }

    public void updateProduct(Long productId, String name, Integer price, Long categoryId, String description, String imageBase64) {
        Product product = getProductOrThrow(productId);

        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (imageBase64 != null) {
            product.setImageBase64(imageBase64);
        }
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));
            product.setCategory(category);
        }

        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalStateException("Продукт с таким ID не существует");
        }
        productRepository.deleteById(productId);
    }

    public Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Продукт не найден"));
    }
}
