package in.co.dreamsdoor.WeatherApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    static {
        Font.loadFont(MainApp.class.getResource("/font/fafonts.ttf").toExternalForm(), 10);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader
                .load(Objects.requireNonNull(getClass()
                        .getResource("/fxml/Scene.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Weather Information");
        stage.setScene(scene);
        stage.show();
    }

}
