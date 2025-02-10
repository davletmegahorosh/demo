package com.example.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories/")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }

    @PostMapping(path = "add/")
    public void addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path = "delete/{category_id}/")
    public void deleteCategory(@PathVariable("category_id") Long category_id) {
        categoryService.deleteCategory(category_id);
    }

    @PutMapping(path = "edit/{category_id}/")
    public void updateCategory(
            @PathVariable("category_id") Long category_id,
            @RequestParam(required = false) String name) {
        categoryService.updateCategory(category_id, name);
    }

    @GetMapping("{category_id}")
    public CategoryDetailsResponse getCategoryDetails(@PathVariable("category_id") Long categoryId) {
        return categoryService.getCategoryDetails(categoryId);
    }
}
