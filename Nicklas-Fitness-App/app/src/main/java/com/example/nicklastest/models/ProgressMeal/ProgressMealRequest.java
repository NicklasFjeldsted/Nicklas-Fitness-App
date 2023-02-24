package com.example.nicklastest.models.ProgressMeal;

import com.example.nicklastest.models.SizedProduct.SizedProductRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgressMealRequest {
    @SerializedName("mealTimeID")
    @Expose
    public Integer mealTimeID;
    @SerializedName("sizedProducts")
    @Expose
    public List<SizedProductRequest> sizedProducts;

    public ProgressMealRequest(Integer mealTimeID, List<SizedProductRequest> sizedProducts) {
        this.mealTimeID = mealTimeID;
        this.sizedProducts = sizedProducts;
    }

    public Integer getMealTimeID() {
        return mealTimeID;
    }

    public void setMealTimeID(Integer mealTimeID) {
        this.mealTimeID = mealTimeID;
    }

    public List<SizedProductRequest> getSizedProducts() {
        return sizedProducts;
    }

    public void setSizedProducts(List<SizedProductRequest> sizedProducts) {
        this.sizedProducts = sizedProducts;
    }
}
