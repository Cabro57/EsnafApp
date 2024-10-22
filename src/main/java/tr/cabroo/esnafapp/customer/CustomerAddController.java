package tr.cabroo.esnafapp.customer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class CustomerAddController {

    @FXML
    private TextField name_surname_textfield;

    @FXML
    private TextField phone_no_textfield;

    @FXML
    private TextField address_textfield;

    @FXML
    public void AddCustomer() {
        String name_surname = name_surname_textfield.getText();
        String phone_no = phone_no_textfield.getText();
        String address = address_textfield.getText();

        Customer customer = new Customer(1, name_surname, name_surname, phone_no, address, 0);

        new CustomerDatabase().save(customer);

        System.out.println("Müşteri: " + name_surname + "\nTelefon No.: " + phone_no + "\nAdres: " + address);
    }
}
