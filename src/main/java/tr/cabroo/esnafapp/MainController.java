package tr.cabroo.esnafapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button close_button;

    @FXML
    private Button minimize_button;

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
}
