package com.example.socialmediaapp;

public class User {

    private String username;
    private String email;
    private String dateOfBirth;
    private String password;

    public User() {}

    public User(String username, String email, String dateOfBirth, String password) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }
}
