package com.example.demo.product;
import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.product.Product;
import com.example.demo.product.ProductService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("house_list/")
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Product> productList() {
        return productService.productsList();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product, @RequestParam Long categoryId) {
        // Получение текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User currentUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));

        // Установка автора и категории продукта
        product.setAuthor(currentUser);
        product.setCategory(category);

        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long productId) {
        productService.deleteBook(productId);
    }

    @PutMapping(path = "{product_id}")
    public void updateProduct(
            @PathVariable("product_id") Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer categoryId) {
        productService.updateProduct(productId, name, price, categoryId);
    }
}
