package com.someone.someone.myuasnew.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMyGuitar {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<MyGuitar> result = null;

    /**
     *
     * @param result
     * @param status
     */
    public GetMyGuitar(String status, List<MyGuitar> result) {
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

    public GetMyGuitar withStatus(String status) {
        this.status = status;
        return this;
    }

    public List<MyGuitar> getResult() {
        return result;
    }

    public void setResult(List<MyGuitar> result) {
        this.result = result;
    }

    public GetMyGuitar withResult(List<MyGuitar> result) {
        this.result = result;
        return this;
    }

}
