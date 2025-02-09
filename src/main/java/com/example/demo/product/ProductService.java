package com.example.demo.product;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
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

    // Получение списка продуктов с только названием и ценой
    public List<ProductListResponse> productsList() {
        return productRepository.findAll().stream()
                .map(product -> new ProductListResponse(product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public ProductDetailsResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Продукт с таким ID не найден"));

        // Формирование ответа с полной информацией
        return new ProductDetailsResponse(
                product.getName(),
                product.getPrice(),
                product.getCategory().getName(),  // assuming category has a `getName()` method
                product.getAuthor().getFirstname() + " " + product.getAuthor().getLastname()  // assuming `User` has `getFirstname()` and `getLastname()`
        );
    }

    public void addNewProduct(Product product) {
        Optional<Product> productByName = productRepository.findProductByName(product.getName());
        if (productByName.isPresent()) {
            throw new IllegalStateException("Продукт с таким названием уже существует");
        }

        productRepository.save(product);
    }

    public void updateProduct(Long productId, String name, Integer price, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Продукт не найден"));

        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));
            product.setCategory(category);
        }

        productRepository.save(product);
    }

    public void deleteProduct(Long book_id) {
        boolean exists = productRepository.existsById(book_id);
        if (!exists) {
            throw new IllegalStateException("product with id " + " doesnt'exists");
        }
        productRepository.deleteById(book_id);
    }
}
