module org.example.felahasznaloi_rendszer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens org.example.felahasznaloi_rendszer to javafx.fxml;
    exports org.example.felahasznaloi_rendszer;
}