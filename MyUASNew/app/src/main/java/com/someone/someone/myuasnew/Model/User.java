package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private String idUser;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public User(String idUser, String fullname, String username, String password, String email,
                String photoUrl, String action) {
        this.idUser= idUser;
        this.fullname= fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser= idUser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
