package com.example.nicklastest.models.ProgressMeal;

import com.example.nicklastest.models.SizedProduct.SizedProductRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgressMealPatchRequest {
    @SerializedName("sizedProducts")
    @Expose
    private List<SizedProductRequest> sizedProducts;

    public ProgressMealPatchRequest(List<SizedProductRequest> sizedProducts) {
        this.sizedProducts = sizedProducts;
    }

    public List<SizedProductRequest> getSizedProducts() {
        return sizedProducts;
    }

    public void setSizedProducts(List<SizedProductRequest> sizedProducts) {
        this.sizedProducts = sizedProducts;
    }
}
