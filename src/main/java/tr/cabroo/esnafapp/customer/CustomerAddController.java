package tr.cabroo.esnafapp.customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import tr.cabroo.esnafapp.MainController;


public class CustomerAddController {

    @FXML
    private TextField name_surname_textfield;

    @FXML
    private TextField phone_no_textfield;

    @FXML
    private TextField address_textfield;

    private Runnable onCustomerAddedCallback;

    public void setOnCustomerAddedCallback(Runnable callback) {
        this.onCustomerAddedCallback = callback;
    }


    @FXML
    public void AddCustomer() {
        String name_surname = name_surname_textfield.getText();
        String[] name_surnameArray = name_surname.split(" ");

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < name_surnameArray.length - 1; i++) {
            name_surnameArray[i] = name_surnameArray[i].substring(0, 1).toUpperCase() + name_surnameArray[i].substring(1).toLowerCase();
            name.append(name_surnameArray[i]).append(" ");
        }
        name = new StringBuilder(name.toString().trim());


        String surname = name_surnameArray[name_surnameArray.length - 1];
        surname =surname.toUpperCase();

        String phone_no = phone_no_textfield.getText();

        String address = address_textfield.getText();
        address = address.substring(0, 1).toUpperCase() + address.substring(1).toLowerCase();

        Customer customer = new Customer(name.toString(), surname, phone_no, address);

        new CustomerDatabase().save(customer);

        clearInputFields();

        if (onCustomerAddedCallback != null) {
            onCustomerAddedCallback.run();
        }

        TabPane anayuz = (TabPane) name_surname_textfield.getScene().lookup("#anayuz");

        MainController.findCustomersTab(anayuz, "Müşteriler");
    }

    private void clearInputFields() {
        name_surname_textfield.clear();
        phone_no_textfield.clear();
        address_textfield.clear();
    }
}
