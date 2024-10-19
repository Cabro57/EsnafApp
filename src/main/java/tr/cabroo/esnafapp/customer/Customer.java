package tr.cabroo.esnafapp.customer;

import java.sql.DriverManager;

public class Customer {
    private int ID;
    private String Name;
    private String Surname;
    private String PhoneNumber;
    private String Address;
    private int Debit;

    public Customer(int id, String name, String surname, String PhoneNumber, String address, int debit) {
        this.ID = id;
        this.Name = name;
        this.Surname = surname;
        this.PhoneNumber = PhoneNumber;
        this.Address = address;
        this.Debit = debit;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getDebit() {
        return Debit;
    }

    public void setDebit(int debit) {
        Debit = debit;
    }
}
