package tr.cabroo.esnafapp.transactions;

import java.time.LocalDateTime;

public class Transaction {
    private int ID;
    private String TransactionType;
    private int CustomerID;
    private int ProductID;
    private int Price;
    private LocalDateTime TransactionDate;

    public Transaction(int ID, String transactionType, int customerID, int productID, int price, LocalDateTime transactionDate) {
        this.ID = ID;
        this.TransactionType = transactionType;
        this.CustomerID = customerID;
        this.ProductID = productID;
        this.Price = price;
        this.TransactionDate = transactionDate;
    }

    public int getID() {
        return ID;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        this.TransactionType = transactionType;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        this.CustomerID = customerID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        this.ProductID = productID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.TransactionDate = transactionDate;
    }
}
