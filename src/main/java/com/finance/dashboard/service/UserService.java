package com.finance.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.model.User;
import com.finance.dashboard.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User create(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User updateStatus(Long id, boolean status) {
        User user = repo.findById(id).orElseThrow();
        user.setActive(status);
        return repo.save(user);
    }
}