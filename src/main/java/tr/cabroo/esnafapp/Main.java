package tr.cabroo.esnafapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    // Sürükleme için x ve y koordinatları
    private double xcord = 0;
    private double ycord = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-ui.fxml"));
        Pane view = fxmlLoader.load();  // Ana sahnedeki kök node'u alıyoruz
        Scene scene = new Scene(view);

        primaryStage.setTitle("EsnafApp");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);  // Pencere çerçevesiz olacak

        // Sürükleme işlemi için mouse olaylarını ayarlıyoruz
        /*view.setOnMousePressed(event -> {
            xcord = event.getSceneX();
            ycord = event.getSceneY();
        });

        view.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xcord);
            primaryStage.setY(event.getScreenY() - ycord);
        });*/

        primaryStage.show();
    }
}
