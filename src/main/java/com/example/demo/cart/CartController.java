package com.example.demo.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addBookToCart(@RequestParam Long userId, @RequestParam Long bookId) {
        cartService.addBookToCart(userId, bookId);
    }

    @GetMapping("/view")
    public List<CartItem> viewCart(@RequestParam Long userId) {
        return cartService.viewCart(userId);
    }

    @PostMapping("/purchase")
    public void purchaseCart(@RequestParam Long userId) {
        cartService.purchaseCart(userId);
    }
}
