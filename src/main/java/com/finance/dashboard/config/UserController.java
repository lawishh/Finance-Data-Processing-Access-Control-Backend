package com.finance.dashboard.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.model.User;
import com.finance.dashboard.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return service.getAll();
    }
}