package com.example.trash2cash;

public class User {
    String username, email, points;

    public User() {
    }

    public User(String username, String email, String points) {
        this.username = username;
        this.email = email;
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
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
