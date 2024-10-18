package tr.cabroo.esnafapp.product;

import tr.cabroo.esnafapp.customer.Customer;
import tr.cabroo.esnafapp.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDatabase extends DatabaseManager {
    Product product;

    public ProductDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Product ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " code TEXT NOT NULL,"
                + " name TEXT NOT NULL,"
                + " brand TEXT NOT NULL,"
                + " units TEXT NOT NULL"
                + " stock INTEGER NOT NULL"
                + " profit_margin FLOAT NOT NULL"
                + " purchase_price FLOAT NOT NULL"
                + " sale_price FLOAT NOT NULL"
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

    public void save(Product product) {
        String insertSql = "INSERT INTO Customer(code, name, brand, stock, profit_margin, purchase_price, sale_price) VALUES(?, ?, ?, ?)";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(insertSql);

            preparedStatement.setString(1, product.getCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getUnits());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setFloat(6, product.getProfitMargin());
            preparedStatement.setFloat(7, product.getPurchasePrice());
            preparedStatement.setFloat(8, product.getSalePrice());

            preparedStatement.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }

    public Product load(int id) {
        String selectSql = "SELECT * FROM Customer WHERE id = ?";
        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String units = resultSet.getString("units");
                int stock = resultSet.getInt("stock");
                float profit_margin = resultSet.getFloat("profit_margin");
                float purchase_price = resultSet.getFloat("purchase_price");
                float sale_price = resultSet.getFloat("sale_price");

                product = new Product(id, code, name, brand, units, stock, profit_margin, purchase_price, sale_price);
            }
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }

        return product;
    }

    public ArrayList<Product> loadAll() {
        ArrayList<Product> products = new ArrayList<>();
        String selectSql = "SELECT * FROM Customer";  // SQL sorgusu düzeltildi

        try {
            login();
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String units = resultSet.getString("units");
                int stock = resultSet.getInt("stock");
                float profit_margin = resultSet.getFloat("profit_margin");
                float purchase_price = resultSet.getFloat("purchase_price");
                float sale_price = resultSet.getFloat("sale_price");

                product = new Product(id, code, name, brand, units, stock, profit_margin, purchase_price, sale_price);

                products.add(product);
            }
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }

        return products;
    }

    public void update(Customer customer) {
        String updateSql = "UPDATE Product SET code = ?, name = ?, brand = ?, units = ?, stock = ?, profit_margin = ?, purchase_margin = ?, sale_price = ? WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(updateSql);

            preparedStatement.setString(1, product.getCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getUnits());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setFloat(6, product.getProfitMargin());
            preparedStatement.setFloat(7, product.getPurchasePrice());
            preparedStatement.setFloat(8, product.getSalePrice());
            preparedStatement.setInt(9, product.getID());

            preparedStatement.executeUpdate();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }
}
