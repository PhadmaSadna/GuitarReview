package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelReview {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Review mReview;
    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Review getReview() {
        return mReview;
    }

    public void setPembelian(Review review) {
        mReview= review;
    }

}
