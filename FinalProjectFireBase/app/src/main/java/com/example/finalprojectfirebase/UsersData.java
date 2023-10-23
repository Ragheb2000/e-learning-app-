package com.example.finalprojectfirebase;

public class UsersData {
    private   String email;
    private  String userId;
    private String username;
     private String userType;

    public UsersData() {
    }

    public UsersData(String email, String userId, String username ,String userType) {
        this.email = email;
        this.userId = userId;
        this.username = username;
        this.userType=userType;

    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
