package com.example.nicklastest.models.ProgressMeal;

import com.example.nicklastest.models.SizedProduct.SizedProductRequest;

import java.util.List;

public class ProgressMealRequest {
    public Integer mealTimeID;
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
