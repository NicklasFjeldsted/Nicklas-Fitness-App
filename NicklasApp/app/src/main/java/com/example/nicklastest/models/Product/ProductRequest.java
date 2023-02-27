package com.example.nicklastest.models.Product;

public class ProductRequest {
    private String ProductCode;
    private String ProductName;
    private String ProductManufacturer;
    private Double EnergyAmount;
    private Double FatAmount;
    private Double SaturatedFatAmount;
    private Double CarbohydrateAmount;
    private Double SugarAmount;
    private Double FiberAmount;
    private Double ProteinAmount;
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
