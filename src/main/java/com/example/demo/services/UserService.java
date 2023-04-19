package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        if(user.getLogin().length() < 3 || user.getPassword().length() < 3) {
            return "bad";
        }

        userRepository.save(user);
        return "good";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
