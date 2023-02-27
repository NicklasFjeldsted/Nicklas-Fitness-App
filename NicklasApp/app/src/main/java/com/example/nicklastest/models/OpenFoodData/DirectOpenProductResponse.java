package com.example.nicklastest.models.OpenFoodData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectOpenProductResponse {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("status_verbose")
    @Expose
    private String statusVerbose;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusVerbose() {
        return statusVerbose;
    }

    public void setStatusVerbose(String statusVerbose) {
        this.statusVerbose = statusVerbose;
    }
}
