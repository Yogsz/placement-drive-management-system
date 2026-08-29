package com.example.campus_placement_tracker.controller;

import com.example.campus_placement_tracker.entity.UserEntity;
import com.example.campus_placement_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserEntity createUser(
            @Valid @RequestBody UserEntity user) {

        return userService.createUser(user);
    }


    @GetMapping
    public List<UserEntity> getAllUsers() {

        return userService.getAllUsers();
    }


    @GetMapping("/{userId}")
    public UserEntity getUserById(
            @PathVariable int userId) {

        return userService.getUserById(userId);
    }


    @GetMapping("/email/{email}")
    public UserEntity getUserByEmail(
            @PathVariable String email) {

        return userService.getUserByEmail(email);
    }


    @PutMapping("/{userId}")
    public UserEntity updateUser(
            @PathVariable int userId,
            @Valid @RequestBody UserEntity user) {

        return userService.updateUser(
                userId,
                user
        );
    }


    @DeleteMapping("/{userId}")
    public String deleteUser(
            @PathVariable int userId) {

        boolean deleted =
                userService.deleteUser(userId);

        if (deleted) {
            return "User deleted successfully";
        }

        return "User not found";
    }
}