package com.example.nicklastest.models.SizedProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SizedProductRequest {
    @SerializedName("servingSize")
    @Expose
    public Double servingSize;
    @SerializedName("productID")
    @Expose
    public Integer productID;

    public SizedProductRequest(Double servingSize, Integer productID) {
        this.servingSize = servingSize;
        this.productID = productID;
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
