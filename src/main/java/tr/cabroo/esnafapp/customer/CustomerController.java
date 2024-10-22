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

        name_surname_tablecolumn.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue();
            String combinedInfo = customer.getName() + " - " + customer.getSurname();
            return new SimpleStringProperty(combinedInfo);
        });
        address_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("Addres"));
        phone_no_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        debit_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("Debit"));

        CustomerDatabase customerdb = new CustomerDatabase();
        ArrayList<Customer> customers = customerdb.loadAll();
        if (customers != null) {
            ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
            customers_tableview.setItems(customerList);

        }
    }

    @FXML
    public void AddCustomer() {
        String name_surname = name_surname_textfield.getText();
        String phone_no = phone_no_textfield.getText();
        String address = address_textfield.getText();

        Customer customer = new Customer(1, name_surname, name_surname, phone_no, address, 0);

        new CustomerDatabase().save(customer);

        System.out.println("Müşteri: " + name_surname + "\nTelefon No.: " + phone_no + "\nAdres: " + address);
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
