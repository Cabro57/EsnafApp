package tr.cabroo.esnafapp.product;

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
                + " barcode TEXT NOT NULL,"
                + " name TEXT NOT NULL,"
                + " brand TEXT NOT NULL,"
                + " unit TEXT NOT NULL,"
                + " stock INTEGER NOT NULL,"
                + " purchase_price FLOAT NOT NULL,"
                + " sale_price FLOAT NOT NULL"
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

    public void save(Product product) {
        String insertSql = "INSERT INTO Product(barcode, name, brand, unit, stock, purchase_price, sale_price) VALUES(?, ?, ?, ?, ?, ? ,?)";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(insertSql);

            preparedStatement.setString(1, product.getBarcode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getUnit());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setFloat(6, product.getPurchasePrice());
            preparedStatement.setFloat(7, product.getSalePrice());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }

    public Product load(int id) {
        String selectSql = "SELECT * FROM Product WHERE id = ?";
        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                String barcode = resultSet.getString("barcode");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String unit = resultSet.getString("unit");
                int stock = resultSet.getInt("stock");
                float purchase_price = resultSet.getFloat("purchase_price");
                float sale_price = resultSet.getFloat("sale_price");

                product = new Product(id, barcode, name, brand, unit, stock, purchase_price, sale_price);
            }

            preparedStatement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }

        return product;
    }

    public ArrayList<Product> loadAll() {
        ArrayList<Product> products = new ArrayList<>();
        String selectSql = "SELECT * FROM Product";  // SQL sorgusu düzeltildi

        try {
            login();
            Statement statement = this.connect.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("barcode");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String unit = resultSet.getString("unit");
                int stock = resultSet.getInt("stock");
                float purchase_price = resultSet.getFloat("purchase_price");
                float sale_price = resultSet.getFloat("sale_price");

                product = new Product(id, code, name, brand, unit, stock, purchase_price, sale_price);

                products.add(product);
            }

            statement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }

        return products;
    }

    public void update(Product product) {
        String updateSql = "UPDATE Product SET barcode = ?, name = ?, brand = ?, unit = ?, stock = ?, purchase_price = ?, sale_price = ? WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(updateSql);

            preparedStatement.setString(1, product.getBarcode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getUnit());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setFloat(6, product.getPurchasePrice());
            preparedStatement.setFloat(7, product.getSalePrice());
            preparedStatement.setInt(8, product.getID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (SQLException e) {
            System.out.println("Oops, Bu bir sorun: " + e);
        }
    }

    public void delete(int id) {
        String deleteSql = "DELETE FROM Product WHERE id = ?";

        try {
            login();
            PreparedStatement preparedStatement = this.connect.prepareStatement(deleteSql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            close();
        } catch (Exception e) {
            System.out.println("Oops, Bu bir hata: " + e);
        }
    }

    public void deleteAll() {
        String deleteSql = "DELETE FROM Product";

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
