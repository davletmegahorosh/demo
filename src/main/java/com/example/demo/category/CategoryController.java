package com.example.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories_list/")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Получение всех категорий
    @GetMapping
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }

    // Добавление новой категории
    @PostMapping
    public void addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
    }

    // Удаление категории по ID
    @DeleteMapping(path = "{category_id}")
    public void deleteCategory(@PathVariable("category_id") Long category_id) {
        categoryService.deleteCategory(category_id);
    }

    // Обновление категории
    @PutMapping(path = "{category_id}")
    public void updateCategory(
            @PathVariable("category_id") Long category_id,
            @RequestParam(required = false) String name) {
        categoryService.updateCategory(category_id, name);
    }

    // Получение категории с продуктами
    @GetMapping("category/{category_id}")
    public CategoryDetailsResponse getCategoryDetails(@PathVariable("category_id") Long categoryId) {
        return categoryService.getCategoryDetails(categoryId);
    }
}
