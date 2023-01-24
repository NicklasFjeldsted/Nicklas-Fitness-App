package models.Product;

public class ProductRequest {
    private String ProductName;
    private String ProductCode;
    private double EnergyAmount;
    private double FatAmount;
    private double SaturatedFatAmount;
    private double CarbohydrateAmount;
    private double SugarAmount;
    private double FiberAmount;
    private double ProteinAmount;
    private double SaltAmount;

    public ProductRequest(String productName, String productCode, double energyAmount, double fatAmount, double saturatedFatAmount, double carbohydrateAmount, double sugarAmount, double fiberAmount, double proteinAmount, double saltAmount) {
        ProductName = productName;
        ProductCode = productCode;
        EnergyAmount = energyAmount;
        FatAmount = fatAmount;
        SaturatedFatAmount = saturatedFatAmount;
        CarbohydrateAmount = carbohydrateAmount;
        SugarAmount = sugarAmount;
        FiberAmount = fiberAmount;
        ProteinAmount = proteinAmount;
        SaltAmount = saltAmount;
    }



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public double getEnergyAmount() {
        return EnergyAmount;
    }

    public void setEnergyAmount(double energyAmount) {
        EnergyAmount = energyAmount;
    }

    public double getFatAmount() {
        return FatAmount;
    }

    public void setFatAmount(double fatAmount) {
        FatAmount = fatAmount;
    }

    public double getSaturatedFatAmount() {
        return SaturatedFatAmount;
    }

    public void setSaturatedFatAmount(double saturatedFatAmount) {
        SaturatedFatAmount = saturatedFatAmount;
    }

    public double getCarbohydrateAmount() {
        return CarbohydrateAmount;
    }

    public void setCarbohydrateAmount(double carbohydrateAmount) {
        CarbohydrateAmount = carbohydrateAmount;
    }

    public double getSugarAmount() {
        return SugarAmount;
    }

    public void setSugarAmount(double sugarAmount) {
        SugarAmount = sugarAmount;
    }

    public double getFiberAmount() {
        return FiberAmount;
    }

    public void setFiberAmount(double fiberAmount) {
        FiberAmount = fiberAmount;
    }

    public double getProteinAmount() {
        return ProteinAmount;
    }

    public void setProteinAmount(double proteinAmount) {
        ProteinAmount = proteinAmount;
    }

    public double getSaltAmount() {
        return SaltAmount;
    }

    public void setSaltAmount(double saltAmount) {
        SaltAmount = saltAmount;
    }
}
