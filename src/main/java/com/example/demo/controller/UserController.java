package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}