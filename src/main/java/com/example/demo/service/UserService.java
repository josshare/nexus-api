package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id);
    }

    public String deleteUser(String id) {
        return userRepository.delete(id);
    }

    public String updateUser(String id, User user) {
        return userRepository.update(id, user);
    }
}