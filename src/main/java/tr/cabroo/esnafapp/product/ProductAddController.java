package tr.cabroo.esnafapp.product;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import tr.cabroo.esnafapp.MainController;

public class ProductAddController {

    @FXML
    private TextField name_textfield;

    @FXML
    private TextField brand_textfield;

    @FXML
    private TextField unit_textfield;

    @FXML
    private TextField stock_textfield;

    @FXML
    private TextField purchase_price_textfield;

    @FXML
    private TextField sale_price_textfield;




    private Runnable onProductAddedCallback;

    public void setOnProductAddedCallback(Runnable callback) {
        this.onProductAddedCallback = callback;
    }


    @FXML
    public void AddProduct() {
        String input_name = name_textfield.getText();
        String[] name_Array = input_name.split(" ");

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < name_Array.length; i++) {
            name_Array[i] = name_Array[i].substring(0, 1).toUpperCase() + name_Array[i].substring(1).toLowerCase();
            name.append(name_Array[i]).append(" ");
        }
        name = new StringBuilder(name.toString().trim());

        String brand = brand_textfield.getText();
        String unit = unit_textfield.getText();
        String stock = stock_textfield.getText();
        String purhcase_price = purchase_price_textfield.getText();
        String sale_price = sale_price_textfield.getText();

        Product product = new Product(name.toString(), brand, unit, Integer.parseInt(stock), Float.parseFloat(purhcase_price), Float.parseFloat(sale_price));

        new ProductDatabase().save(product);

        clearInputFields();

        if (onProductAddedCallback != null) {
            onProductAddedCallback.run();
        }

        TabPane anayuz = (TabPane) name_textfield.getScene().lookup("#anayuz");

        MainController.findProductsTab(anayuz, "Ürünler");
    }

    private void clearInputFields() {
        name_textfield.clear();
        brand_textfield.clear();
        unit_textfield.clear();
        stock_textfield.clear();
        purchase_price_textfield.clear();
        sale_price_textfield.clear();
    }
}
