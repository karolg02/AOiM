package org.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Załaduj pierwszą scenę
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1, 1200, 700);

        // Pobierz kontrolery z obu scen
        Scene1Controller scene1Controller = loader1.getController();

        // Ustaw stage i sceny w obu kontrolerach
        scene1Controller.setStageAndScenes(primaryStage, scene1);

        // Wyświetl pierwszą scenę
        primaryStage.setTitle("TeacherManager");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
