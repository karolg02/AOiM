package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene1Controller implements Initializable {
    private Scene scene;

    @FXML
    private Button addGroupButton;
    @FXML
    private TextField addGroupField;
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
    @FXML
    private Button deleteGroupButton;

    private final ClassContainer classContainer = new ClassContainer();

    public void setStageAndScenes(Stage stage, Scene scene) {
        this.scene = scene;

        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene == this.scene) {
                groupTab.refresh();
            }
        });
    }

    private void openGroupDetails(ClassTeacher group) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
            Parent root = loader.load();

            Scene2Controller scene2Controller = loader.getController();
            scene2Controller.setGroup(group);

            Scene scene2 = new Scene(root);
            Stage currentStage = (Stage) groupTab.getScene().getWindow();

            scene2Controller.setStageAndScene1(currentStage, groupTab.getScene());
            scene2Controller.loadTeachersFromDatabase();
            scene2Controller.loadRates();
            currentStage.setScene(scene2);
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error loading Scene2.fxml: " + e.getMessage());
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText("Grupa o takiej nazwie już istnieje!");
        alert.showAndWait();
    }

    private void addNewGroup(String groupName, ObservableList<ClassTeacher> data) {
        try (Session session = Connector.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ClassTeacher existingGroup = session.createQuery(
                            "FROM ClassTeacher WHERE groupName = :groupName", ClassTeacher.class)
                    .setParameter("groupName", groupName)
                    .uniqueResult();

            if (existingGroup == null) {
                ClassTeacher newGroup = new ClassTeacher();
                newGroup.setGroupName(groupName);
                newGroup.setCapacity(10);

                session.save(newGroup);
                transaction.commit();

                data.add(newGroup);

                addGroupField.clear();
            } else {
                showAlert();
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas dodawania grupy: " + e.getMessage());
        }
    }

    private void deleteGroup(ClassTeacher selectedGroup, ObservableList<ClassTeacher> data) {
        try (Session session = Connector.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            if (selectedGroup != null) {
                session.remove(selectedGroup);
                transaction.commit();
                data.remove(selectedGroup);
                groupTab.refresh();
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas usuwania grupy: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ObservableList<ClassTeacher> data = FXCollections.observableArrayList(classContainer.getClassTeachers());
        groupFilled.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getFilledPercentage()).asObject());
        groupCapacity.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCapacity()).asObject());
        groupTab.setItems(data);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterTable(newValue, data));

        addGroupButton.setOnAction(event -> {
            String groupName = addGroupField.getText().trim();
            if (!groupName.isEmpty()) {
                addNewGroup(groupName, data);
            }
            groupTab.refresh();
            searchField.clear();
        });

        deleteGroupButton.setOnAction(event -> {
            ClassTeacher selectedGroup = groupTab.getSelectionModel().getSelectedItem();
            deleteGroup(selectedGroup, data);
        });

        groupTab.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ClassTeacher selectedGroup = groupTab.getSelectionModel().getSelectedItem();
                if (selectedGroup != null) {
                    openGroupDetails(selectedGroup);
                }
            }
        });
    }

    private void filterTable(String searchText, ObservableList<ClassTeacher> data) {
        ObservableList<ClassTeacher> filteredData = FXCollections.observableArrayList();

        if (searchText == null || searchText.isEmpty()) {
            filteredData.addAll(data);
        } else {
            for (ClassTeacher classTeacher : data) {
                if (classTeacher.getGroupName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredData.add(classTeacher);
                }
            }
        }
        groupTab.setItems(filteredData);
    }
}
