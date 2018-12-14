package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guitar {

    @SerializedName("review_id")
    @Expose
    private String idReview;
    @SerializedName("review_tittle")
    @Expose
    private String reviewTittle;
    @SerializedName("merk_type")
    @Expose
    private String merkType;
    @SerializedName("jns_gitar")
    @Expose
    private String jnsGitar;
    @SerializedName("review_detail")
    @Expose
    private String reviewDetail;
    @SerializedName("harga")
    @Expose
    private String harga;

    public Guitar(String idReview, String reviewTittle, String merkType, String jnsGitar, String
            reviewDetail, String harga) {
        super();
        this.idReview= idReview;
        this.reviewTittle= reviewTittle;
        this.merkType= merkType;
        this.jnsGitar= jnsGitar;
        this.reviewDetail= reviewDetail;
        this.harga = harga;
    }

    public String getIdReview() {
        return idReview;
    }

    public void setIdReview(String idReview) {
        this.idReview= idReview;
    }

    public Guitar withIdReview(String idReview) {
        this.idReview= idReview;
        return this;
    }

    public String getReviewTittle() {
        return reviewTittle;
    }

    public void setReviewTittle(String reviewTittle) {
        this.reviewTittle= reviewTittle;
    }

    public Guitar withReviewTittle(String reviewTittle) {
        this.reviewTittle = reviewTittle;
        return this;
    }

    public String getMerkType() {
        return merkType;
    }

    public void setMerkType(String merkType) {
        this.merkType= merkType;
    }

    public Guitar withMerkType(String merkType) {
        this.merkType = merkType;
        return this;
    }

    public String getJnsGitar() {
        return jnsGitar;
    }

    public void setJnsGitar(String jnsGitar) {
        this.jnsGitar= jnsGitar;
    }

    public Guitar withJnsGitar(String jnsGitar) {
        this.jnsGitar = jnsGitar;
        return this;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail= reviewDetail;
    }

    public Guitar withReviewDetail(String reviewDetail) {
        this.reviewDetail= reviewDetail;
        return this;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Guitar withHarga(String harga) {
        this.harga = harga;
        return this;
    }

}