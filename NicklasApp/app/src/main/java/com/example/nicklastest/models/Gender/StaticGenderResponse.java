package com.example.nicklastest.models.Gender;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StaticGenderResponse implements Serializable {

    @SerializedName("genderID")
    @Expose
    private Integer genderID;
    @SerializedName("genderName")
    @Expose
    private String genderName;

    public Integer getGenderID() {
        return genderID;
    }

    public void setGenderID(Integer genderID) {
        this.genderID = genderID;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}