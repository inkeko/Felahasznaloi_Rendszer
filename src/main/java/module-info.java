module org.example.felahasznaloi_rendszer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires jakarta.persistence;

    opens org.example.felahasznaloi_rendszer to javafx.fxml;
    exports org.example.felahasznaloi_rendszer;
}