package org.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;

public class HelloController {

    private Stage stage;
    private Scene scene1;
    private Scene scene2;
    @FXML
    private Label label;

    // Metoda do ustawienia stage i scen
    public void setStageAndScenes(Stage stage, Scene scene1, Scene scene2) {
        this.stage = stage;
        this.scene1 = scene1;
        this.scene2 = scene2;
    }

    // Przełącz na scenę 2
    @FXML
    protected void switchToScene2() {
        if (stage != null && scene2 != null) {
            stage.setScene(scene2);
        }
    }

    // Przełącz na scenę 1
    @FXML
    protected void switchToScene1() {
        if (stage != null && scene1 != null) {
            stage.setScene(scene1);
        }
    }

    int zmienna = 1;
    @FXML
    protected void elo(){
        HashMap<Integer, String> capitalCities = new HashMap<Integer, String>();

        capitalCities.put(1, "Get");
        capitalCities.put(2, "The");
        capitalCities.put(3, "London");
        capitalCities.put(4, "Look");

        this.label.setText(capitalCities.get(zmienna));
        zmienna++;
        if(zmienna == 5){
            zmienna = 1;
        }
    }

    @FXML
    protected void wypisz() {
        System.out.println("Wypisz");
    }
}
