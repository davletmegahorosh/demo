package com.example.demo.category;
import com.example.demo.category.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(Category category) {
        Optional<Category> categoryByName = categoryRepository
                .findCategoryByName(category.getName());
        if (categoryByName.isPresent()) {
            throw new IllegalStateException("Книга с таким названием уже существуется");

        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long category_id) {
        boolean exists = categoryRepository.existsById(category_id);
        if (!exists) {
            throw new IllegalStateException("category with id " + " doesnt'exists");
        }
        categoryRepository.deleteById(category_id);
    }


    @Transactional
    public void updateCategory(Long category_id, String name) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new IllegalStateException(
                        "category with id " + category_id + " doesn't exists"));

        if (name != null && name.length() > 0 && !Objects.equals(category.getName(), name)) {
            category.setName(name);
        }


        categoryRepository.save(category);
    }
}