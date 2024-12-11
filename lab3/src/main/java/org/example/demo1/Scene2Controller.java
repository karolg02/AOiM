package org.example.demo1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Scene2Controller {
    private Scene scene1;
    private Stage stage;
    private Teacher selectedTeacher;
    private ObservableList<Teacher> teacherList;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> conditionField;
    @FXML
    private TextField birthYearField;
    @FXML
    private TextField salaryField;
    @FXML
    private javafx.scene.control.Button addButton;
    @FXML
    private javafx.scene.control.Button updateButton;
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, String> teacherFirstName;
    @FXML
    private TableColumn<Teacher, String> teacherLastName;
    @FXML
    private TableColumn<Teacher, String> teacherCondition;
    @FXML
    private TableColumn<Teacher, Integer> teacherBirthYear;
    @FXML
    private TableColumn<Teacher, Double> teacherSalary;
    @FXML
    private TextField searchTeacherField;

    private ClassTeacher group;

    public void setStageAndScene1(Stage stage, Scene scene1) {
        this.stage = stage;
        this.scene1 = scene1;

        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        returnToScene1();
                    }
                });
            }
        });
    }

    private void returnToScene1() {
        if (stage != null && scene1 != null) {
            stage.setScene(scene1);
        }
    }

    public void setGroup(ClassTeacher group) {
        this.group = group;
        teacherList = FXCollections.observableArrayList(group.teachers);
        teacherTable.setItems(teacherList);

        teacherTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateForm(newValue);
            }
        });
    }
    private void populateForm(Teacher teacher) {
        selectedTeacher = teacher;
        firstNameField.setText(teacher.getImie());
        lastNameField.setText(teacher.getNazwisko());
        conditionField.setValue(teacher.getTeacherCondition().name());
        birthYearField.setText(String.valueOf(teacher.getRokUrodzenia()));
        salaryField.setText(String.valueOf(teacher.getWynagrodzenie()));
    }

    private void addTeacher() {
        if (group.getFilledPercentage() >= 100) {
            showAlert("Grupa jest pełna, nie można dodać więcej nauczycieli.");
            return;
        }

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String condition = conditionField.getSelectionModel().getSelectedItem();
        int birthYear;
        double salary;

        try {
            birthYear = Integer.parseInt(birthYearField.getText());
            salary = Double.parseDouble(salaryField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        Teacher existingTeacher = findTeacher(firstName, lastName, birthYear);
        if (existingTeacher != null) {
            showAlert("Nauczyciel o tych danych już istnieje w tej grupie.");
            return;
        }

        Teacher newTeacher = new Teacher(firstName, lastName,TeacherCondition.valueOf(condition.toUpperCase()), birthYear, salary);
        teacherList.add(newTeacher);
        group.teachers.add(newTeacher);
        clearForm();
    }

    private Teacher findTeacher(String firstName, String lastName, int birthYear) {
        for (Teacher teacher : group.teachers) {
            if (teacher.getImie().equals(firstName) && teacher.getNazwisko().equals(lastName) && teacher.getRokUrodzenia() == birthYear) {
                return teacher;
            }
        }
        return null;
    }

    private void filterTeachers(String searchText) {
        ObservableList<Teacher> filteredTeachers = FXCollections.observableArrayList();

        for (Teacher teacher : group.teachers) {
            if (teacher.getImie().toLowerCase().contains(searchText.toLowerCase())) {
                filteredTeachers.add(teacher);
            }
        }

        teacherTable.setItems(filteredTeachers);
    }


    private void updateTeacher() {
        if (selectedTeacher != null) {
            selectedTeacher.setImie(firstNameField.getText());
            selectedTeacher.setNazwisko(lastNameField.getText());
            selectedTeacher.setTeacherCondition(TeacherCondition.valueOf(conditionField.getSelectionModel().getSelectedItem().toUpperCase()));

            try {
                selectedTeacher.setRokUrodzenia(Integer.parseInt(birthYearField.getText()));
                selectedTeacher.setWynagrodzenie(Double.parseDouble(salaryField.getText()));
            } catch (NumberFormatException e) {
                showError("Niepoprawne dane liczbowe.");
                return;
            }

            teacherTable.refresh();
            clearForm();
        } else {
            showError("Nie wybrano nauczyciela do edycji.");
        }
    }

    @FXML
    private void deleteSelectedTeacher() {
        Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            group.teachers.remove(selectedTeacher);

            teacherTable.getItems().remove(selectedTeacher);
            teacherTable.refresh();
        } else {
            showAlert("Nie wybrano nauczyciela do usunięcia.");
        }
    }



    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        conditionField.getSelectionModel().selectFirst();
        birthYearField.clear();
        salaryField.clear();
        selectedTeacher = null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    public void initialize() {
        conditionField.setItems(FXCollections.observableArrayList(
                TeacherCondition.OBECNY.name(),
                TeacherCondition.DELEGACJA.name(),
                TeacherCondition.CHORY.name(),
                TeacherCondition.NIEOBECNY.name()
        ));

        conditionField.getSelectionModel().selectFirst();

        teacherFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImie()));
        teacherLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNazwisko()));
        teacherCondition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeacherCondition().name()));
        teacherBirthYear.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRokUrodzenia()).asObject());
        teacherSalary.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWynagrodzenie()).asObject());

        addButton.setOnAction(event -> addTeacher());

        updateButton.setOnAction(event -> updateTeacher());

        searchTeacherField.textProperty().addListener((observable, oldValue, newValue) -> filterTeachers(newValue));
    }
}