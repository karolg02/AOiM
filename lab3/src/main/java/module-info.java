module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.desktop;


    opens org.example.demo1 to javafx.fxml,org.hibernate.orm.core;
    exports org.example.demo1;
}