package com.example.nicklastest.models.SizedProduct;

import com.example.nicklastest.models.Product.StaticProductResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectSizedProductResponse {
    @SerializedName("sizedProductID")
    @Expose
    private Integer sizedProductID;
    @SerializedName("servingSize")
    @Expose
    private Double servingSize;
    @SerializedName("product")
    @Expose
    private StaticProductResponse product;

    public Integer getSizedProductID() {
        return sizedProductID;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public StaticProductResponse getProduct() {
        return product;
    }
}
