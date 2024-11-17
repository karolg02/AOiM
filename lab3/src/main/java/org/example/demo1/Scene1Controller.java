package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    ClassContainer classContainer = ClassContainer.getInstance();

    public void setStageAndScenes(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }

    @FXML
    private TableColumn<ClassTeacher, Integer> groupCapacity;

    @FXML
    private TableColumn<ClassTeacher, Double> groupFilled;

    @FXML
    private TableColumn<ClassTeacher, String> groupName;

    @FXML
    private TableView<ClassTeacher> groupTab;

    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ObservableList<ClassTeacher> data = FXCollections.observableArrayList(classContainer.getClassTeachers());
        groupFilled.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getFilledPercentage()).asObject());
        groupCapacity.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());
        groupTab.setItems(data);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue, data);
        });
    }

    private void filterTable(String searchText, ObservableList<ClassTeacher> data) {
        ObservableList<ClassTeacher> filteredData = FXCollections.observableArrayList();

        if (searchText == null || searchText.isEmpty()) {
            filteredData.addAll(data);
        } else {
            for (ClassTeacher classTeacher : data) {
                if (classTeacher.groupName.toLowerCase().contains(searchText.toLowerCase())) {
                    filteredData.add(classTeacher);
                }
            }
        }
        groupTab.setItems(filteredData);
    }
}

