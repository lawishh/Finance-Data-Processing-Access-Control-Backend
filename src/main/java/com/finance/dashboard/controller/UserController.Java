package com.finance.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.model.User;
import com.finance.dashboard.service.UserService;

import java.util.List;

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

    @PutMapping("/status/{id}")
    public User updateStatus(@PathVariable Long id,
                            @RequestParam boolean active) {
        return service.updateStatus(id, active);
    }
}