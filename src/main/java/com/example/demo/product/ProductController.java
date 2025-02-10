package com.example.demo.product;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product/")
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
    public List<ProductListResponse> productList() {
        return productService.productsList();
    }

    @GetMapping("{product_id}")
    public ProductDetailsResponse getProductById(@PathVariable("product_id") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping(path = "add/")
    public void addProduct(@RequestBody Map<String, Object> requestBody) {
        String productName = (String) requestBody.get("name");
        Integer productPrice = (Integer) requestBody.get("price");
        Long categoryId = Long.valueOf((Integer) requestBody.get("categoryId"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User currentUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));

        Product product = new Product(productName, productPrice);
        product.setAuthor(currentUser);
        product.setCategory(category);

        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "delete/{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long productId) {
        Product product = productService.getProductOrThrow(productId);
        checkUserAuthorization(product);
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "edit/{product_id}")
    public void updateProduct(
            @PathVariable("product_id") Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageBase64) {

        Product product = productService.getProductOrThrow(productId);
        checkUserAuthorization(product);
        productService.updateProduct(productId, name, price, categoryId, description, imageBase64);
    }

    private void checkUserAuthorization(Product product) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!product.getAuthor().getEmail().equals(currentUserEmail)) {
            throw new SecurityException("У вас нет прав на редактирование или удаление этого продукта");
        }
    }
}
