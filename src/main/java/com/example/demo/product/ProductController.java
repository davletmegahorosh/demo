package com.example.demo.product;

import com.example.demo.product.Product;
import com.example.demo.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("house_list/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> productList() {
        return productService.productsList();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long book_id){
        productService.deleteBook(book_id);
    }

    @PutMapping(path = "{product_id}")
    public void updateProduct(
            @PathVariable("product_id") Long product_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int price){
        productService.updateProduct(product_id, name, price);
    }
}
