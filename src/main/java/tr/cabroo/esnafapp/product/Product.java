package tr.cabroo.esnafapp.product;

public class Product {
    int ID;
    String Code;
    String Name;
    String Brand;
    String Units;
    int Stock;
    float ProfitMargin;
    float PurchasePrice;
    float SalePrice;

    public Product(int ID, String code, String name, String brand, String units, int stock, float profitMargin, float purchasePrice, float salePrice) {
        this.ID = ID;
        Code = code;
        Name = name;
        Brand = brand;
        Units = units;
        Stock = stock;
        ProfitMargin = profitMargin;
        PurchasePrice = purchasePrice;
        SalePrice = salePrice;
    }

    public int getID() {
        return ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public float getProfitMargin() {
        return ProfitMargin;
    }

    public void setProfitMargin(float profitMargin) {
        ProfitMargin = profitMargin;
    }

    public float getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public float getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(float salePrice) {
        SalePrice = salePrice;
    }
}
