package com.example.nicklastest.models.PlanProgress;

import com.example.nicklastest.models.ProgressMeal.ProgressMealRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanProgressRequest {
    @SerializedName("progressDate")
    @Expose
    private String progressDate;
    @SerializedName("progressMeals")
    @Expose
    private List<ProgressMealRequest> progressMeals;
    @SerializedName("currentWeight")
    @Expose
    private Integer currentWeight;
    @SerializedName("userPlanID")
    @Expose
    private Integer userPlanID;

    public PlanProgressRequest(String progressDate, List<ProgressMealRequest> progressMeals, Integer currentWeight, Integer userPlanID) {
        this.progressDate = progressDate;
        this.progressMeals = progressMeals;
        this.currentWeight = currentWeight;
        this.userPlanID = userPlanID;
    }

    public String getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(String progressDate) {
        this.progressDate = progressDate;
    }

    public List<ProgressMealRequest> getProgressMeals() {
        return progressMeals;
    }

    public void setProgressMeals(List<ProgressMealRequest> progressMeals) {
        this.progressMeals = progressMeals;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getUserPlanID() {
        return userPlanID;
    }

    public void setUserPlanID(Integer userPlanID) {
        this.userPlanID = userPlanID;
    }
}
