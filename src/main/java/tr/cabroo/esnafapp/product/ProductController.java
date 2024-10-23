package tr.cabroo.esnafapp.product;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import tr.cabroo.esnafapp.Main;

import java.io.IOException;
import java.util.ArrayList;

public class ProductController {

    @FXML
    private VBox products_vbox;

    @FXML
    private TableView<Product> products_tableview;

    @FXML
    private TableColumn<Product, String> name_tablecolumn;

    @FXML
    private TableColumn<Product, String> brand_tablecolumn;

    @FXML
    private TableColumn<Product, String> unit_tablecolumn;

    @FXML
    private TableColumn<Product, String> stock_tablecolumn;

    @FXML
    private TableColumn<Product, String> purchase_price_tablecolumn;

    @FXML
    private TableColumn<Product, String> margin_price_tablecolumn;

    @FXML
    private TableColumn<Product, String> sale_price_tablecolumn;

    public void initialize() {
        configureTableColumns();
        loadProducts();
    }

    // Tablo sütunlarını yapılandırma
    private void configureTableColumns() {
        name_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        brand_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        unit_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUnit()));
        stock_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));
        purchase_price_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPurchasePrice())));
        margin_price_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(differencefind(cellData.getValue().getPurchasePrice(),cellData.getValue().getSalePrice())));
        sale_price_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalePrice())));
    }

    // Müşterileri veritabanından yükleme
    private void loadProducts() {
        ProductDatabase productdb = new ProductDatabase();
        ArrayList<Product> products = productdb.loadAll();
        if (products != null) {
            ObservableList<Product> productList = FXCollections.observableArrayList(products);
            products_tableview.setItems(productList);
        }
    }

    @FXML
    public void AddPaneAddProduct() {
        TabPane anayuz = (TabPane) products_vbox.getScene().lookup("#anayuz");

        Tab productTab = new Tab("Ürün Ekle");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("product/product-add-tab.fxml"));
        try {
            productTab.setContent(fxmlLoader.load());
            anayuz.getTabs().add(productTab);
            anayuz.getSelectionModel().select(productTab);

            ProductAddController addController = fxmlLoader.getController();
            addController.setOnProductAddedCallback(this::loadProducts);

        } catch (IOException e) {
            System.err.println("FXML dosyası yüklenirken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
            showAlert("Hata", "Ürün ekleme sekmesi yüklenemedi. Lütfen tekrar deneyin.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String differencefind(float purchase, float sale) {
        float difference;
        difference = ((sale-purchase)/purchase)*100;

        return String.format("%.2f%%", difference);
    }
}
