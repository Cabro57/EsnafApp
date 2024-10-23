package tr.cabroo.esnafapp.customer;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tr.cabroo.esnafapp.Main;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerController {

    @FXML
    private TextField name_surname_textfield;

    @FXML
    private TextField phone_no_textfield;

    @FXML
    private TextField address_textfield;

    @FXML
    private VBox customers_vbox;

    @FXML
    private TableView<Customer> customers_tableview;

    @FXML
    private TableColumn<Customer, String> name_surname_tablecolumn;

    @FXML
    private TableColumn<Customer, String> address_tablecolumn;

    @FXML
    private TableColumn<Customer, String> phone_no_tablecolumn;

    @FXML
    private TableColumn<Customer, String> debit_tablecolumn;

    public void initialize() {
        configureTableColumns();
        loadCustomers();
    }

    // Tablo sütunlarını yapılandırma
    private void configureTableColumns() {
        name_surname_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName() + " " + cellData.getValue().getSurname()));
        address_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        phone_no_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        debit_tablecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDebit())));
    }

    // Müşterileri veritabanından yükleme
    private void loadCustomers() {
        CustomerDatabase customerdb = new CustomerDatabase();
        ArrayList<Customer> customers = customerdb.loadAll();
        if (customers != null) {
            ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
            customers_tableview.setItems(customerList);
        }
    }

    @FXML
    public void AddPaneAddCostumer() {
        TabPane anayuz = (TabPane) customers_vbox.getScene().lookup("#anayuz");

        Tab customerTab = new Tab("Müşteri Ekle");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customer/customer-add-tab.fxml"));
        try {
            customerTab.setContent(fxmlLoader.load());
            anayuz.getTabs().add(customerTab);
            anayuz.getSelectionModel().select(customerTab);

            CustomerAddController addController = fxmlLoader.getController();
            addController.setOnCustomerAddedCallback(this::loadCustomers);

        } catch (IOException e) {
            System.err.println("FXML dosyası yüklenirken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
            showAlert("Hata", "Müşteri ekleme sekmesi yüklenemedi. Lütfen tekrar deneyin.");
        }
    }



    // Kullanıcıya bir alert göstermek için yardımcı metot
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
