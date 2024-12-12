package org.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connector connector = new Connector();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);

        Scene1Controller scene1Controller = loader1.getController();

        scene1Controller.setStageAndScenes(primaryStage, scene1);

        primaryStage.setTitle("TeacherManager");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
