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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Button addButton;
    @FXML
    private Button updateButton;
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
    private SessionFactory sessionFactory;

    public Scene2Controller() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

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
        teacherList = FXCollections.observableArrayList();

        try (Session session = sessionFactory.openSession()) {
            teacherList.setAll(session.createQuery("from Teacher", Teacher.class).list());
        }

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

    @FXML
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
            showError("Niepoprawne dane liczbowe.");
            return;
        }

        Teacher newTeacher = new Teacher(firstName, lastName, TeacherCondition.valueOf(condition.toUpperCase()), birthYear, salary);
        newTeacher.setClassTeacher(group);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(newTeacher);
            transaction.commit();
            teacherList.add(newTeacher);
        }

        clearForm();
    }

    private Teacher findTeacher(String firstName, String lastName, int birthYear) {
        for (Teacher teacher : teacherList) {
            if (teacher.getImie().equals(firstName) && teacher.getNazwisko().equals(lastName) && teacher.getRokUrodzenia() == birthYear) {
                return teacher;
            }
        }
        return null;
    }

    private void filterTeachers(String searchText) {
        ObservableList<Teacher> filteredTeachers = FXCollections.observableArrayList();

        try (Session session = sessionFactory.openSession()) {
            Query<Teacher> query = session.createQuery("from Teacher where lower(imie) like :searchText", Teacher.class);
            query.setParameter("searchText", "%" + searchText.toLowerCase() + "%");
            filteredTeachers.setAll(query.list());
        }

        teacherTable.setItems(filteredTeachers);
    }
    @FXML
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

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(selectedTeacher);
                transaction.commit();
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
            teacherList.remove(selectedTeacher);

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(selectedTeacher);
                transaction.commit();
            }

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

    public void loadTeachersFromDatabase() {
        if (group == null) {
            System.out.println("Group is not set. Skipping loading teachers.");
            return;
        }

        try (Session session = Connector.getSessionFactory().openSession()) {
            List<Teacher> teachersFromDb = session.createQuery("from Teacher ", Teacher.class).list();
            List<Teacher> finalGroup = new ArrayList<>();

            for (Teacher teacher : teachersFromDb) {
                if (Objects.equals(teacher.getClassTeacher().getId(), group.getId())) {
                    finalGroup.add(teacher);
                }
            }

            teacherList.setAll(finalGroup);
            teacherTable.setItems(teacherList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        loadTeachersFromDatabase();
        teacherList = FXCollections.observableArrayList();

        conditionField.setItems(FXCollections.observableArrayList(
                TeacherCondition.OBECNY.name(),
                TeacherCondition.DELEGACJA.name(),
                TeacherCondition.CHORY.name(),
                TeacherCondition.NIEOBECNY.name()
        ));

        teacherFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImie()));
        teacherLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNazwisko()));
        teacherCondition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeacherCondition().name()));
        teacherBirthYear.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRokUrodzenia()).asObject());
        teacherSalary.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWynagrodzenie()).asObject());

        searchTeacherField.textProperty().addListener((observable, oldValue, newValue) -> filterTeachers(newValue));
        teacherTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateForm(newValue);
            }
        });

        teacherTable.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                deleteSelectedTeacher();
            }
        });
    }
}
