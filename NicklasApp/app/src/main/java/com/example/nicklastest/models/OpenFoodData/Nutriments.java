package com.example.nicklastest.models.OpenFoodData;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nutriments {

    @SerializedName("carbohydrates_100g")
    private Double carbohydrates100g;

    @SerializedName("energy-kcal_100g")
    private Double energyKcal100g;

    @SerializedName("energy_100g")
    private Double energy100g;

    @SerializedName("fat_100g")
    private Double fat100g;

    @SerializedName("fiber_100g")
    private Double fiber100g;

    @SerializedName("proteins_100g")
    private Double proteins100g;

    @SerializedName("saturated-fat_100g")
    private Double saturatedFat100g;

    @SerializedName("sugars_100g")
    private Double sugars100g;

    @SerializedName("salt_100g")
    private Double salt100g;

    public Double getCarbohydrates100g() {
        return carbohydrates100g;
    }

    public void setCarbohydrates100g(Double carbohydrates100g) {
        this.carbohydrates100g = carbohydrates100g;
    }

    public Double getEnergyKcal100g() {
        return energyKcal100g;
    }

    public void setEnergyKcal100g(Double energyKcal100g) {
        this.energyKcal100g = energyKcal100g;
    }

    public Double getEnergy100g() {
        return energy100g;
    }

    public void setEnergy100g(Double energy100g) {
        this.energy100g = energy100g;
    }

    public Double getFat100g() {
        return fat100g;
    }

    public void setFat100g(Double fat100g) {
        this.fat100g = fat100g;
    }

    public Double getFiber100g() {
        return fiber100g;
    }

    public void setFiber100g(Double fiber100g) {
        this.fiber100g = fiber100g;
    }

    public Double getProteins100g() {
        return proteins100g;
    }

    public void setProteins100g(Double proteins100g) {
        this.proteins100g = proteins100g;
    }

    public Double getSaturatedFat100g() {
        return saturatedFat100g;
    }

    public void setSaturatedFat100g(Double saturatedFat100g) {
        this.saturatedFat100g = saturatedFat100g;
    }

    public Double getSugars100g() {
        return sugars100g;
    }

    public void setSugars100g(Double sugars100g) {
        this.sugars100g = sugars100g;
    }

    public Double getSalt100g() {
        return salt100g;
    }

    public void setSalt100g(Double salt100g) {
        this.salt100g = salt100g;
    }
}
