package com.example.nicklastest.models.UserPlan;

import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectUserPlanResponse {
    @SerializedName("userPlanID")
    @Expose
    private Integer userPlanID;
    @SerializedName("startWeight")
    @Expose
    private Integer startWeight;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("weightGoal")
    @Expose
    private Integer weightGoal;
    @SerializedName("weeklyGoal")
    @Expose
    private Double weeklyGoal;
    @SerializedName("planProgress")
    @Expose
    private List<DirectPlanProgressResponse> planProgress;
    @SerializedName("activityLevelID")
    @Expose
    private Integer activityLevelID;

    public DirectUserPlanResponse(Integer userPlanID, Integer startWeight, String startDate, Integer weightGoal, Double weeklyGoal, List<DirectPlanProgressResponse> planProgress, Integer activityLevelID) {
        this.userPlanID = userPlanID;
        this.startWeight = startWeight;
        this.startDate = startDate;
        this.weightGoal = weightGoal;
        this.weeklyGoal = weeklyGoal;
        this.planProgress = planProgress;
        this.activityLevelID = activityLevelID;
    }

    public Integer getUserPlanID() {
        return userPlanID;
    }

    public void setUserPlanID(Integer userPlanID) {
        this.userPlanID = userPlanID;
    }

    public Integer getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(Integer startWeight) {
        this.startWeight = startWeight;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Integer weightGoal) {
        this.weightGoal = weightGoal;
    }

    public Double getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(Double weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public List<DirectPlanProgressResponse> getPlanProgress() {
        return planProgress;
    }

    public void setPlanProgress(List<DirectPlanProgressResponse> planProgress) {
        this.planProgress = planProgress;
    }

    public Integer getActivityLevelID() {
        return activityLevelID;
    }

    public void setActivityLevelID(Integer activityLevelID) {
        this.activityLevelID = activityLevelID;
    }
}
