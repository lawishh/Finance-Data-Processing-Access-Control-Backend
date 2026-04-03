package com.finance.dashboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userss")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active = true;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public Role getRole() { return role; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setRole(Role role) { this.role = role; }
    public void setActive(boolean active) { this.active = active; }
}