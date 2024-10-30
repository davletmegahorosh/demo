package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        String response = userService.registerUser(request.getUsername(), request.getPassword());

        if (response.equals("Пользователь уже существует")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 409 Conflict
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        String response = userService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response); // 200 OK
    }
}
