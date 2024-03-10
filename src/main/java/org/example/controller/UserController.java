package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // 권한이거나 또는 글쓴이 본인인지 확이후 업데이트
    @PostMapping("/update/{userId}")
    @PreAuthorize("#userId == principal.id or hasAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }



}
