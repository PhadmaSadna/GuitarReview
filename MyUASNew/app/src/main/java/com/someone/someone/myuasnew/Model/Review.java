package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("id_transac")
    private String id_transac;
    @SerializedName("user_id_fk")
    private String user_id_fk;
    @SerializedName("review_id_fk")
    private String review_id_fk;
    @SerializedName("jml_vote")
    private int jml_vote;

    public Review(String id_transac, String user_id_fk, String review_id_fk, int jml_vote) {
        this.id_transac= id_transac;
        this.user_id_fk= user_id_fk;
        this.review_id_fk= review_id_fk;
        this.jml_vote= jml_vote;
    }

    public String getId_transac() {
        return id_transac;
    }

    public void setId_transac(String id_transac) {
        this.id_transac= id_transac;
    }

    public String getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(String user_id_fk) {
        this.user_id_fk= user_id_fk;
    }

    public String getReview_id_fk() {
        return review_id_fk;
    }

    public void setReview_id_fk(String review_id_fk) {
        this.review_id_fk= review_id_fk;
    }

    public int getJml_vote() {
        return jml_vote;
    }

    public void setJml_vote(int jml_vote) {
        this.jml_vote= jml_vote;
    }

}
