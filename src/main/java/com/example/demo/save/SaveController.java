package com.example.demo.save;
import com.example.demo.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/saves")
public class SaveController {

    private final SaveService saveService;

    @Autowired
    public SaveController(SaveService saveService) {
        this.saveService = saveService;
    }

    @GetMapping
    public List<Product> getSavedProducts() {
        return saveService.getSavedProductsForCurrentUser();
    }

    @PostMapping("/{productId}")
    public void saveProduct(@PathVariable Long productId) {
        saveService.addProductToSave(productId);
    }

    @DeleteMapping("/{productId}")
    public void removeProductFromSave(@PathVariable Long productId) {
        saveService.removeProductFromSave(productId);
    }
}