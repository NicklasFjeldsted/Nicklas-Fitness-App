package com.example.nicklastest.models.Product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductRequest {
    @SerializedName("productName")
    @Expose
    private String ProductName;
    @SerializedName("productManufacturer")
    @Expose
    private String ProductManufacturer;
    @SerializedName("productCode")
    @Expose
    private String ProductCode;
    @SerializedName("energyAmount")
    @Expose
    private Double EnergyAmount;
    @SerializedName("fatAmount")
    @Expose
    private Double FatAmount;
    @SerializedName("saturatedFatAmount")
    @Expose
    private Double SaturatedFatAmount;
    @SerializedName("carbohydrateAmount")
    @Expose
    private Double CarbohydrateAmount;
    @SerializedName("sugarAmount")
    @Expose
    private Double SugarAmount;
    @SerializedName("fiberAmount")
@   Expose
    private Double FiberAmount;
    @SerializedName("proteinAmount")
    @Expose
    private Double ProteinAmount;
    @SerializedName("saltAmount")
@   Expose
    private Double SaltAmount;

    public ProductRequest(String productCode, String productName, String productManufacturer, Double energyAmount, Double fatAmount, Double saturatedFatAmount, Double carbohydrateAmount, Double sugarAmount, Double fiberAmount, Double proteinAmount, Double saltAmount) {
        ProductCode = productCode;
        ProductName = productName;
        ProductManufacturer = productManufacturer;
        EnergyAmount = energyAmount;
        FatAmount = fatAmount;
        SaturatedFatAmount = saturatedFatAmount;
        CarbohydrateAmount = carbohydrateAmount;
        SugarAmount = sugarAmount;
        FiberAmount = fiberAmount;
        ProteinAmount = proteinAmount;
        SaltAmount = saltAmount;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductManufacturer() {
        return ProductManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        ProductManufacturer = productManufacturer;
    }

    public Double getEnergyAmount() {
        return EnergyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        EnergyAmount = energyAmount;
    }

    public Double getFatAmount() {
        return FatAmount;
    }

    public void setFatAmount(Double fatAmount) {
        FatAmount = fatAmount;
    }

    public Double getSaturatedFatAmount() {
        return SaturatedFatAmount;
    }

    public void setSaturatedFatAmount(Double saturatedFatAmount) {
        SaturatedFatAmount = saturatedFatAmount;
    }

    public Double getCarbohydrateAmount() {
        return CarbohydrateAmount;
    }

    public void setCarbohydrateAmount(Double carbohydrateAmount) {
        CarbohydrateAmount = carbohydrateAmount;
    }

    public Double getSugarAmount() {
        return SugarAmount;
    }

    public void setSugarAmount(Double sugarAmount) {
        SugarAmount = sugarAmount;
    }

    public Double getFiberAmount() {
        return FiberAmount;
    }

    public void setFiberAmount(Double fiberAmount) {
        FiberAmount = fiberAmount;
    }

    public Double getProteinAmount() {
        return ProteinAmount;
    }

    public void setProteinAmount(Double proteinAmount) {
        ProteinAmount = proteinAmount;
    }

    public Double getSaltAmount() {
        return SaltAmount;
    }

    public void setSaltAmount(Double saltAmount) {
        SaltAmount = saltAmount;
    }
}
