package com.example.trash2cash;

public class User {
    String username, email;
    int points;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.points = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
