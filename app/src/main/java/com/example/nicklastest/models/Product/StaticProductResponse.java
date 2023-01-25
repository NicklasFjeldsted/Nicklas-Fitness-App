package com.example.nicklastest.models.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaticProductResponse {
    @SerializedName("productID")
    @Expose
    private Integer productID;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("energyAmount")
    @Expose
    private Integer energyAmount;
    @SerializedName("fatAmount")
    @Expose
    private Integer fatAmount;
    @SerializedName("saturatedFatAmount")
    @Expose
    private Integer saturatedFatAmount;
    @SerializedName("carbohydrateAmount")
    @Expose
    private Integer carbohydrateAmount;
    @SerializedName("sugarAmount")
    @Expose
    private Integer sugarAmount;
    @SerializedName("fiberAmount")
    @Expose
    private Integer fiberAmount;
    @SerializedName("proteinAmount")
    @Expose
    private Integer proteinAmount;
    @SerializedName("saltAmount")
    @Expose
    private Integer saltAmount;

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

    public Integer getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Integer energyAmount) {
        this.energyAmount = energyAmount;
    }

    public Integer getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(Integer fatAmount) {
        this.fatAmount = fatAmount;
    }

    public Integer getSaturatedFatAmount() {
        return saturatedFatAmount;
    }

    public void setSaturatedFatAmount(Integer saturatedFatAmount) {
        this.saturatedFatAmount = saturatedFatAmount;
    }

    public Integer getCarbohydrateAmount() {
        return carbohydrateAmount;
    }

    public void setCarbohydrateAmount(Integer carbohydrateAmount) {
        this.carbohydrateAmount = carbohydrateAmount;
    }

    public Integer getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(Integer sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    public Integer getFiberAmount() {
        return fiberAmount;
    }

    public void setFiberAmount(Integer fiberAmount) {
        this.fiberAmount = fiberAmount;
    }

    public Integer getProteinAmount() {
        return proteinAmount;
    }

    public void setProteinAmount(Integer proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    public Integer getSaltAmount() {
        return saltAmount;
    }

    public void setSaltAmount(Integer saltAmount) {
        this.saltAmount = saltAmount;
    }
}
