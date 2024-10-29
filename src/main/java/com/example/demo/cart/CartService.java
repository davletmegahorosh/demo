package com.example.demo.cart;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void addBookToCart(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book not found"));

        CartItem cartItem = new CartItem(user, book);
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> viewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        return cartItemRepository.findByUser(user);
    }

    public void purchaseCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        // Здесь можно добавить логику имитации покупки, например, списание средств, создание заказа и т.д.
        cartItemRepository.deleteAll(cartItems); // Очистка корзины после покупки
    }
}
