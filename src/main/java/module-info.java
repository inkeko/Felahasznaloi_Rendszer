module org.example.felahasznaloi_rendszer {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens org.example.controller to javafx.fxml;
    opens org.example.model to jakarta.persistence, org.hibernate.orm.core, javafx.fxml;

    exports org.example;
    exports org.example.controller;
    exports org.example.model;
}


