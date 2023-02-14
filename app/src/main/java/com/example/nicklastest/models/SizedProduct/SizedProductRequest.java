package com.example.nicklastest.models.SizedProduct;

public class SizedProductRequest {
    public Double servingSize;
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
