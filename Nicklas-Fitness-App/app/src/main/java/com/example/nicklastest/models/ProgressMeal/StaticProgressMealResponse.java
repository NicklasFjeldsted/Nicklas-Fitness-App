package com.example.nicklastest.models.ProgressMeal;

import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StaticProgressMealResponse {
    @SerializedName("progressMealID")
    @Expose
    private Integer progressMealID;
    @SerializedName("mealTimeID")
    @Expose
    private Integer mealTimeID;
    @SerializedName("sizedProducts")
    @Expose
    private List<DirectSizedProductResponse> sizedProducts;

    public StaticProgressMealResponse(Integer progressMealID, Integer mealTimeID, List<DirectSizedProductResponse> sizedProducts) {
        this.progressMealID = progressMealID;
        this.mealTimeID = mealTimeID;
        this.sizedProducts = sizedProducts;
    }

    public Integer getProgressMealID() {
        return progressMealID;
    }

    public Integer getMealTimeID() {
        return mealTimeID;
    }

    public void setProgressMealID(Integer progressMealID) {
        this.progressMealID = progressMealID;
    }

    public void setMealTimeID(Integer mealTimeID) {
        this.mealTimeID = mealTimeID;
    }

    public void setSizedProducts(List<DirectSizedProductResponse> sizedProducts) {
        this.sizedProducts = sizedProducts;
    }

    public List<DirectSizedProductResponse> getSizedProducts() {
        return sizedProducts;
    }
}
