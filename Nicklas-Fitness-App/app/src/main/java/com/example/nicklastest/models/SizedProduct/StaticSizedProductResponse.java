package com.example.nicklastest.models.SizedProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticSizedProductResponse {
    @SerializedName("sizedProductID")
    @Expose
    private Integer sizedProductID;
    @SerializedName("servingSize")
    @Expose
    private Double servingSize;
    @SerializedName("productID")
    @Expose
    private Integer productID;

    public Integer getSizedProductID() {
        return sizedProductID;
    }

    public void setSizedProductID(Integer sizedProductID) {
        this.sizedProductID = sizedProductID;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public void setServingSize(Double servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }
}
