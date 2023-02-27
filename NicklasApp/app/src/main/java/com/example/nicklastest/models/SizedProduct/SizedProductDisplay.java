package com.example.nicklastest.models.SizedProduct;

public class SizedProductDisplay {
    private String Name;
    private String Description;
    private Integer CalorieCount;

    public SizedProductDisplay(String name, String description, Integer calorieCount) {
        Name = name;
        Description = description;
        CalorieCount = calorieCount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCalorieCount() {
        return CalorieCount;
    }

    public void setCalorieCount(Integer calorieCount) {
        CalorieCount = calorieCount;
    }
}
