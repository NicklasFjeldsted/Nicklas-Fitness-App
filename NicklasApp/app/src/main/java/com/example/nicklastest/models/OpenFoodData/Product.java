package com.example.nicklastest.models.OpenFoodData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("brand_owner")
    @Expose
    private String brandOwner;

    @SerializedName("generic_name")
    @Expose
    private String genericName;

    @SerializedName("nutriments")
    @Expose
    private Nutriments nutriments;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }
}
