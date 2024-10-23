package tr.cabroo.esnafapp.product;

public class Product {
    int ID;
    String Barcode;
    String Name;
    String Brand;
    String Unit;
    int Stock;
    float PurchasePrice;
    float SalePrice;

    public Product(int ID, String barcode, String name, String brand, String unit, int stock, float purchasePrice, float salePrice) {
        this.ID = ID;
        this.Barcode = barcode;
        this.Name = name;
        this.Brand = brand;
        this.Unit = unit;
        this.Stock = stock;
        this.PurchasePrice = purchasePrice;
        this.SalePrice = salePrice;
    }

    public Product(String name, String brand, String unit, int stock, float purchasePrice, float salePrice) {
        this.Barcode = "5";
        this.Name = name;
        this.Brand = brand;
        this.Unit = unit;
        this.Stock = stock;
        this.PurchasePrice = purchasePrice;
        this.SalePrice = salePrice;
    }

    public int getID() {
        return ID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        this.Barcode = barcode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        this.Unit = unit;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    public float getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.PurchasePrice = purchasePrice;
    }

    public float getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(float salePrice) {
        this.SalePrice = salePrice;
    }
}
