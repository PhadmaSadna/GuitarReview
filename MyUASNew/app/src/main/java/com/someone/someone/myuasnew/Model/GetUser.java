package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetUser {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<User> result = new ArrayList<User>();
    @SerializedName("message")
    private String message;
    public GetUser() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
