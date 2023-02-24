package com.example.nicklastest.models.PlanProgress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticPlanProgressResponse {
    @SerializedName("planProgressID")
    @Expose
    private Integer planProgressID;
    @SerializedName("progressDate")
    @Expose
    private String progressDate;
    @SerializedName("currentWeight")
    @Expose
    private Integer currentWeight;
    @SerializedName("userPlanID")
    @Expose
    private Integer userPlanID;

    public Integer getPlanProgressID() {
        return planProgressID;
    }

    public void setPlanProgressID(Integer planProgressID) {
        this.planProgressID = planProgressID;
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

    public Integer getUserPlanID() {
        return userPlanID;
    }

    public void setUserPlanID(Integer userPlanID) {
        this.userPlanID = userPlanID;
    }
}
