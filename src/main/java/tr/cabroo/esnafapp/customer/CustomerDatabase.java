package tr.cabroo.esnafapp.customer;

import tr.cabroo.esnafapp.database.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDatabase extends DatabaseManager {
    Customer customer;

    public CustomerDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Customer ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " surname TEXT NOT NULL,"
                + " address TEXT NOT NULL,"
                + " debit INTEGER NOT NULL"
                + ");";

        try {
            login();
            Statement statement = this.connect.createStatement();
            statement.execute(sql);
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }
    }

    public void save(Customer customer) {
        String insertSql = "INSERT INTO Customer(name, surname, address, debit) VALUES(?, ?, ?, ?)";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(insertSql);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getDebit());

            preparedStatement.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }

    public Customer load(int id) {
        String selectSql = "SELECT * FROM Customer WHERE id = ?";
        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String address = resultSet.getString("address");
                int debit = resultSet.getInt("debit");

                customer = new Customer(id, name, surname, address, debit);
            }
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }

        return customer;
    }

    public ArrayList<Customer> loadAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        String selectSql = "SELECT * FROM Customer";  // SQL sorgusu düzeltildi

        try {
            login();
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String address = resultSet.getString("address");
                int debit = resultSet.getInt("debit");

                customer = new Customer(id, name, surname, address, debit);

                customers.add(customer);
            }
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }

        return customers;
    }

    public void update(Customer customer) {
        String updateSql = "UPDATE Customer SET name = ?, surname = ?, address = ?, debit = ? WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(updateSql);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getDebit());
            preparedStatement.setInt(5, customer.getID());

            preparedStatement.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }
}