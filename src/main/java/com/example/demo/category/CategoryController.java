package com.example.demo.category;
import com.example.demo.category.Category;
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

    @GetMapping
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }

    @PostMapping
    public void addNewCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path = "{category_id}")
    public void deleteCategory(@PathVariable("category_id") Long category_id){
        categoryService.deleteCategory(category_id);
    }

    @PutMapping(path = "{category_id}")
    public void updateCategory(
            @PathVariable("category_id") Long category_id,
            @RequestParam(required = false) String name){
        categoryService.updateCategory(category_id, name);
    }
}
