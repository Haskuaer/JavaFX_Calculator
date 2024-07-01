package calc.javafx_calculator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Run extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource("calc.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setTitle("Calculator");
        stage.setScene(scene);

        Controler controler = fxmlLoader.getController();
        controler.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}