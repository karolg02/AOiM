package org.example.demo1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scene2Controller {
    private Scene scene1;
    private Stage stage;
    private Teacher selectedTeacher;
    private ObservableList<Teacher> teacherList;
    private ObservableList<Rate> rateList;

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
    private TableView<Teacher> teacherTable;
    @FXML
    private TableView<Rate> commentTable;
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
    @FXML
    private Button addCommentButton;
    @FXML
    private ComboBox<Integer> chooseOcena;
    @FXML
    private TextField commentTextArea;
    @FXML
    private TableColumn<Rate, String> komentarz;
    @FXML
    private TableColumn<Rate, Integer> ocena;

    private ClassTeacher group;
    private final SessionFactory sessionFactory;

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

        try (Session session = sessionFactory.openSession()) {
            // Sprawdzenie, czy nauczyciel o takim imieniu i nazwisku już istnieje
            Teacher existingTeacher = session.createQuery(
                            "FROM Teacher WHERE imie = :firstName AND nazwisko = :lastName", Teacher.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .uniqueResult();

            if (existingTeacher != null) {
                showError("Nauczyciel o podanym imieniu i nazwisku już istnieje.");
                return;
            }

            // Dodanie nowego nauczyciela
            Transaction transaction = session.beginTransaction();
            Teacher newTeacher = new Teacher(firstName, lastName, TeacherCondition.valueOf(condition.toUpperCase()), birthYear, salary);
            newTeacher.setClassTeacher(group);

            session.save(newTeacher);
            transaction.commit();

            teacherList.add(newTeacher);
        } catch (Exception e) {
            System.err.println("Błąd podczas dodawania nauczyciela: " + e.getMessage());
        }

        clearForm();
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
    private void addRate() {
        Integer ocena = chooseOcena.getValue();
        String comment = commentTextArea.getText();

        if (ocena == null || comment == null || comment.trim().isEmpty()) {
            System.out.println("Wszystkie pola muszą być wypełnione!");
            return;
        }

        Rate rate = new Rate();
        rate.setValue(ocena);
        rate.setComment(comment);
        rate.setDate(LocalDate.now());

        rate.setGroup(group);

        try (Session session = Connector.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.save(rate);
                transaction.commit();
                if (rateList != null) {
                    rateList.add(rate);
                    commentTable.refresh();
                }
                System.out.println("Ocena została dodana!");
            } catch (Exception e) {
                transaction.rollback();
                System.err.println("Błąd podczas dodawania oceny: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Błąd z sesją Hibernate: " + e.getMessage());
        }
    }

    void loadRates() {
        if (group == null) {
            System.out.println("Group is not set. Skipping loading rates.");
            return;
        }

        try (Session session = sessionFactory.openSession()) {
            Query<Rate> query = session.createQuery("from Rate where group.id = :groupId", Rate.class);
            query.setParameter("groupId", group.getId());

            rateList = FXCollections.observableArrayList(query.list());

            ocena.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rate, Integer>, ObservableValue<Integer>>() {
                public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Rate, Integer> param) {
                    return new SimpleIntegerProperty(param.getValue().getValue()).asObject();
                }
            });

            komentarz.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Rate, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Rate, String> param) {
                    return new SimpleStringProperty(param.getValue().getComment());
                }
            });
            commentTable.setItems(rateList);
        }
    }
    @FXML
    public void exportToCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Teacher";
                List<Teacher> teachers = session.createQuery(hql, Teacher.class).list();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("First Name,Last Name,Condition,Birth Year,Salary");
                    writer.newLine();

                    for (Teacher teacher : teachers) {
                        String line = String.format("%s,%s,%s,%d,%f",
                                teacher.getImie(),
                                teacher.getNazwisko(),
                                teacher.getTeacherCondition().name(),
                                teacher.getRokUrodzenia(),
                                teacher.getWynagrodzenie());
                        writer.write(line);
                        writer.newLine();
                    }

                    System.out.println("Data has been exported to " + file.getPath());
                } catch (IOException e) {
                    System.err.println("Error writing to CSV: " + e.getMessage());
                }
            } catch (Exception e) {
                System.err.println("Error querying database for export: " + e.getMessage());
            }
        } else {
            System.out.println("File save canceled by user.");
        }
    }


    @FXML
    public void initialize() {
        loadTeachersFromDatabase();
        loadRates();
        teacherList = FXCollections.observableArrayList();

        conditionField.setItems(FXCollections.observableArrayList(
                TeacherCondition.OBECNY.name(),
                TeacherCondition.DELEGACJA.name(),
                TeacherCondition.CHORY.name(),
                TeacherCondition.NIEOBECNY.name()
        ));
        chooseOcena.setItems(FXCollections.observableArrayList(
                1,2,3,4,5,6
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
