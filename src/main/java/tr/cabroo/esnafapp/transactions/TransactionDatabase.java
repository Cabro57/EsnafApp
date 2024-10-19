package tr.cabroo.esnafapp.transactions;

import tr.cabroo.esnafapp.database.DatabaseManager;
import tr.cabroo.esnafapp.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionDatabase extends DatabaseManager {
    Transaction transaction;

    public TransactionDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Transactions ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " transaction_type TEXT NOT NULL,"
            + " customer_id INT NOT NULL,"
            + " product_id INT NOT NULL,"
            + " price INT NOT NULL,"
            + " transaction_date TEXT NOT NULL,"
            + " FOREIGN KEY (customer_id) REFERENCES Customers(id),"
            + " FOREIGN KEY (product_id) REFERENCES Product(id)"
            + ");";

        try {
            login();
            Statement statement = this.connect.createStatement();
            statement.execute(sql);
            statement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }
    }

    public void save(Transaction transaction) {
        String insterSql = "INSERT INTO Transactions(transaction_type, customer_id, product_id, price, transaction_date) VALUES(?, ?, ?, ?, ?)";

        login();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(insterSql);

            preparedStatement.setString(1, transaction.getTransactionType());
            preparedStatement.setInt(2, transaction.getCustomerID());
            preparedStatement.setInt(3, transaction.getProductID());
            preparedStatement.setInt(4, transaction.getPrice());
            preparedStatement.setString(5, transaction.getTransactionDate().toString());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }
    }

    public Transaction load(int id) {
        String selectSql = "SELECT * FROM Transactions WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                String transactionType = resultSet.getString("transaction_type");
                int customerID = resultSet.getInt("customer_id");
                int productID = resultSet.getInt("product_id");
                int price = resultSet.getInt("price");
                String transactionDate = resultSet.getString("transaction_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
                LocalDateTime date = LocalDateTime.parse(transactionDate, formatter);
                transaction = new Transaction(id, transactionType, customerID, productID, price, date);
            }
        } catch (Exception e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }

        return transaction;
    }

    public ArrayList<Transaction> loadAll() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String selectSql = "SELECT * FROM Transactions";

        try {
            login();
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String transactionType = resultSet.getString("transaction_type");
                int customerID = resultSet.getInt("customer_id");
                int productID = resultSet.getInt("product_id");
                int price = resultSet.getInt("price");
                String transactionDate = resultSet.getString("transaction_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
                LocalDateTime date = LocalDateTime.parse(transactionDate, formatter);
                transaction = new Transaction(id, transactionType, customerID, productID, price, date);

                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }

        return transactions;
    }

    public void update(Transaction transaction) {
        String updateSql = "UPDATE Transactions SET transaction_type = ?, customer_id = ?, product_id = ?, price = ?, transaction_date = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(updateSql);

            preparedStatement.setString(1, transaction.getTransactionType());
            preparedStatement.setInt(2, transaction.getCustomerID());
            preparedStatement.setInt(3, transaction.getProductID());
            preparedStatement.setInt(4, transaction.getPrice());
            preparedStatement.setString(5, transaction.getTransactionDate().toString());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (Exception e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }

    public void delete(int id) {
        String deleteSql = "DELETE FROM Transactions WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(deleteSql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (Exception e) {
            System.out.println("Oops, bu bir hata: " + e);
        }
    }

    public void deleteAll() {
        String deleteSql = "DELETE FROM Transactions";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(deleteSql);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (Exception e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }
    }
}
