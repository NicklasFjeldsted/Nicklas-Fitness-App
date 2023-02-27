package com.example.nicklastest.models.ProgressMeal;

import com.example.nicklastest.models.MealTime.StaticMealTimeResponse;
import com.example.nicklastest.models.PlanProgress.StaticPlanProgressResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.SizedProduct.StaticSizedProductResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectProgressMealResponse {
    @SerializedName("progressMealID")
    @Expose
    private Integer progressMealID;
    @SerializedName("mealTime")
    @Expose
    private StaticMealTimeResponse mealTime;
    @SerializedName("sizedProducts")
    @Expose
    private List<DirectSizedProductResponse> sizedProducts;
    @SerializedName("planProgress")
    @Expose
    private StaticPlanProgressResponse planProgress;

    public Integer getProgressMealID() {
        return progressMealID;
    }

    public void setProgressMealID(Integer progressMealID) {
        this.progressMealID = progressMealID;
    }

    public StaticMealTimeResponse getMealTime() {
        return mealTime;
    }

    public void setMealTime(StaticMealTimeResponse mealTime) {
        this.mealTime = mealTime;
    }

    public List<DirectSizedProductResponse> getSizedProducts() {
        return sizedProducts;
    }

    public void setSizedProducts(List<DirectSizedProductResponse> sizedProducts) {
        this.sizedProducts = sizedProducts;
    }

    public StaticPlanProgressResponse getPlanProgress() {
        return planProgress;
    }

    public void setPlanProgress(StaticPlanProgressResponse planProgress) {
        this.planProgress = planProgress;
    }

}
