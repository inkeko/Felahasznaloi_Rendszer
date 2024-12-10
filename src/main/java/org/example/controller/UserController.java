package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.service.UserService;

import java.io.IOException;

public class UserController {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final UserService userService = new UserService();

    /**
     * Inicializálás a táblázat betöltéséhez.
     */
    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        users.addAll(userService.getUsers());
        userTable.setItems(users);
    }

    /**
     * Új felhasználó hozzáadása.
     */
    @FXML
    private void handleAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user_add.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Új Felhasználó Hozzáadása");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Frissítjük az adatok listáját
            userTable.setItems(FXCollections.observableArrayList(userService.getUsers()));

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült betölteni az űrlapot.");
        }
    }

    /**
     * Felhasználó módosítása.
     */
    @FXML
    private void handleEditUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Figyelmeztetés", "Nincs kiválasztott felhasználó!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user_add.fxml"));
            Parent root = loader.load();

            // Meglévő adatok betöltése
            UserAddController controller = loader.getController();
            controller.setUserData(selectedUser);

            Stage stage = new Stage();
            stage.setTitle("Felhasználó Szerkesztése");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Adatok frissítése az adatbázisból
            userTable.setItems(FXCollections.observableArrayList(userService.getUsers()));

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hiba", "Nem sikerült betölteni az űrlapot.");
        }
    }

    /**
     * Felhasználó törlése.
     */
    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getUserId());
            userTable.setItems(FXCollections.observableArrayList(userService.getUsers()));
            showAlert("Siker", "Felhasználó törölve!");
        } else {
            showAlert("Figyelmeztetés", "Nincs kiválasztott felhasználó!");
        }
    }

    /**
     * Értesítő ablak megjelenítése.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
