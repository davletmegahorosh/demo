package com.example.demo.category;

import com.example.demo.product.ProductListResponse;
import com.example.demo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(Category category) {
        Optional<Category> categoryByName = categoryRepository
                .findCategoryByName(category.getName());
        if (categoryByName.isPresent()) {
            throw new IllegalStateException("Категория с таким названием уже существует");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long category_id) {
        boolean exists = categoryRepository.existsById(category_id);
        if (!exists) {
            throw new IllegalStateException("Категория с таким ID не найдена");
        }
        categoryRepository.deleteById(category_id);
    }

    public void updateCategory(Long category_id, String name) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));

        if (name != null && !name.isEmpty() && !Objects.equals(category.getName(), name)) {
            category.setName(name);
        }
        categoryRepository.save(category);
    }

    // Получение категории с её продуктами
    public CategoryDetailsResponse getCategoryDetails(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Категория с таким ID не найдена"));

        List<ProductListResponse> productsInCategory = productRepository.findByCategory(category)
                .stream()
                .map(product -> new ProductListResponse(product.getName(), product.getPrice(), product.getImageBase64()))
                .collect(Collectors.toList());

        return new CategoryDetailsResponse(category.getName(), productsInCategory);
    }
}
