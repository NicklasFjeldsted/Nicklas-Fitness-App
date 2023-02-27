package com.example.nicklastest.models.MealTime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticMealTimeResponse {
    @SerializedName("mealTimeID")
    @Expose
    private Integer mealTimeID;
    @SerializedName("mealTimeName")
    @Expose
    private String mealTimeName;

    public StaticMealTimeResponse(Integer mealTimeID, String mealTimeName) {
        this.mealTimeID = mealTimeID;
        this.mealTimeName = mealTimeName;
    }

    public Integer getMealTimeID() {
        return mealTimeID;
    }

    public void setMealTimeID(Integer mealTimeID) {
        this.mealTimeID = mealTimeID;
    }

    public String getMealTimeName() {
        return mealTimeName;
    }

    public void setMealTimeName(String mealTimeName) {
        this.mealTimeName = mealTimeName;
    }
}
