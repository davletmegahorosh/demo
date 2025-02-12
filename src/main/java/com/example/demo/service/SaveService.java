package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Save;
import com.example.demo.repository.ProductRepository;
import com.example.demo.model.User;
import com.example.demo.repository.SaveRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaveService {

    private final SaveRepository saveRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public SaveService(SaveRepository saveRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.saveRepository = saveRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getSavedProductsForCurrentUser() {
        User currentUser = getCurrentAuthenticatedUser();
        return saveRepository.findByAuthor(currentUser).stream()
                .map(Save::getProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addProductToSave(Long productId) {
        User currentUser = getCurrentAuthenticatedUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product with ID " + productId + " not found"));

        Optional<Save> existingSave = saveRepository.findByProductAndAuthor(product, currentUser);
        if (existingSave.isPresent()) {
            throw new IllegalStateException("Product already saved");
        }

        Save save = new Save();
        save.setProduct(product);
        save.setAuthor(currentUser);
        saveRepository.save(save);
    }

    @Transactional
    public void removeProductFromSave(Long productId) {
        User currentUser = getCurrentAuthenticatedUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product with ID " + productId + " not found"));

        Save save = saveRepository.findByProductAndAuthor(product, currentUser)
                .orElseThrow(() -> new IllegalStateException("Product not found in user's saved list"));

        saveRepository.delete(save);
    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }
}