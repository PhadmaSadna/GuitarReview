package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetGuitar {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Guitar> result = null;

    /**
     *
     * @param result
     * @param status
     */
    public GetGuitar(String status, List<Guitar> result) {
        super();
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetGuitar withStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Guitar> getResult() {
        return result;
    }

    public void setResult(List<Guitar> result) {
        this.result = result;
    }

    public GetGuitar withResult(List<Guitar> result) {
        this.result = result;
        return this;
    }

}
