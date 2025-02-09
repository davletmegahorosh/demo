package com.example.demo.product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> productsList() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        Optional<Product> productByName = productRepository
                .findProductByName(product.getName());
        if (productByName.isPresent()) {
            throw new IllegalStateException("Книга с таким названием уже существуется");

        }
        productRepository.save(product);
    }

    public void deleteBook(Long book_id) {
        boolean exists = productRepository.existsById(book_id);
        if (!exists) {
            throw new IllegalStateException("product with id " + " doesnt'exists");
        }
        productRepository.deleteById(book_id);
    }


    @Transactional
    public void updateProduct(Long product_id, String name, int price) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new IllegalStateException(
                        "product with id " + product_id + " doesn't exists"));

        if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }

        if (price > 0 && !Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }


        productRepository.save(product);
    }
}