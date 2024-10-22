package tr.cabroo.esnafapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button close_button;

    @FXML
    private Button minimize_button;

    @FXML
    private TabPane anayuz;



    @FXML
    public void closeApp() {
        // Butonun sahnesini alıp o sahnenin stage'ini kapatıyoruz
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close(); // Uygulamayı kapatır
    }

    @FXML
    public void minimizeApp() {
        Stage stage = (Stage) minimize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void AddPaneCustomers() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customer/customers-tab.fxml"));
        Tab customerTab = new Tab("Müşteriler");
        try {
            customerTab.setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Oops, Bu bir hata: " + e);
            e.printStackTrace();
        }
        anayuz.getTabs().add(customerTab);
        anayuz.getSelectionModel().select(customerTab);
    }
}
