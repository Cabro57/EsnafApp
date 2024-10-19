package tr.cabroo.esnafapp.transactions;

import tr.cabroo.esnafapp.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

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
}
