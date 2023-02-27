package com.example.nicklastest.models.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StaticProductResponse implements Serializable {
    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productManufacturer")
    @Expose
    private String productManufacturer;
    @SerializedName("energyAmount")
    @Expose
    private Double energyAmount;
    @SerializedName("fatAmount")
    @Expose
    private Double fatAmount;
    @SerializedName("saturatedFatAmount")
    @Expose
    private Double saturatedFatAmount;
    @SerializedName("carbohydrateAmount")
    @Expose
    private Double carbohydrateAmount;
    @SerializedName("sugarAmount")
    @Expose
    private Double sugarAmount;
    @SerializedName("fiberAmount")
    @Expose
    private Double fiberAmount;
    @SerializedName("proteinAmount")
    @Expose
    private Double proteinAmount;
    @SerializedName("saltAmount")
    @Expose
    private Double saltAmount;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public Double getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        this.energyAmount = energyAmount;
    }

    public Double getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(Double fatAmount) {
        this.fatAmount = fatAmount;
    }

    public Double getSaturatedFatAmount() {
        return saturatedFatAmount;
    }

    public void setSaturatedFatAmount(Double saturatedFatAmount) {
        this.saturatedFatAmount = saturatedFatAmount;
    }

    public Double getCarbohydrateAmount() {
        return carbohydrateAmount;
    }

    public void setCarbohydrateAmount(Double carbohydrateAmount) {
        this.carbohydrateAmount = carbohydrateAmount;
    }

    public Double getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(Double sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    public Double getFiberAmount() {
        return fiberAmount;
    }

    public void setFiberAmount(Double fiberAmount) {
        this.fiberAmount = fiberAmount;
    }

    public Double getProteinAmount() {
        return proteinAmount;
    }

    public void setProteinAmount(Double proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    public Double getSaltAmount() {
        return saltAmount;
    }

    public void setSaltAmount(Double saltAmount) {
        this.saltAmount = saltAmount;
    }
}
