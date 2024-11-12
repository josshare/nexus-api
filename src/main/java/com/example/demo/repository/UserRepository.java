package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User findById(String id) {
        return dynamoDBMapper.load(User.class, id);
    }

    public String delete(String id) {
        User user = dynamoDBMapper.load(User.class, id);
        dynamoDBMapper.delete(user);
        return "User deleted successfully";
    }

    public String update(String id, User user) {
        dynamoDBMapper.save(user);
        return "User updated successfully";
    }
}