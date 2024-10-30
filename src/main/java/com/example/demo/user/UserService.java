package com.example.demo.user;

import com.example.demo.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return "Пользователь уже существует";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        if (username.equals("admin")) {
            user.setRoles(Set.of("ADMIN"));
        } else {
            user.setRoles(Set.of("USER"));
        }

        userRepository.save(user);

        return jwtUtil.generateToken(username);
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Неверные данные для входа"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        } else {
            throw new IllegalStateException("Неверные данные для входа");
        }
    }
}
