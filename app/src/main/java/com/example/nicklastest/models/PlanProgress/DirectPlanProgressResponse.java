package com.example.nicklastest.models.PlanProgress;

import java.util.List;

import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectPlanProgressResponse {

    @SerializedName("planProgressID")
    @Expose
    private Integer planProgressID;
    @SerializedName("progressMeals")
    @Expose
    private List<StaticProgressMealResponse> progressMeals;
    @SerializedName("progressDate")
    @Expose
    private String progressDate;
    @SerializedName("currentWeight")
    @Expose
    private Integer currentWeight;

    public Integer getPlanProgressID() {
        return planProgressID;
    }

    public void setPlanProgressID(Integer planProgressID) {
        this.planProgressID = planProgressID;
    }

    public List<StaticProgressMealResponse> getProgressMeals() {
        return progressMeals;
    }

    public void setProgressMeals(List<StaticProgressMealResponse> progressMeals) {
        this.progressMeals = progressMeals;
    }

    public String getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(String progressDate) {
        this.progressDate = progressDate;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }
}
