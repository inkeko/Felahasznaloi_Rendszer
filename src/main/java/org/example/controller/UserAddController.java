package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.service.UserService;

public class UserAddController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private final UserService userService = new UserService();
    private User editedUser;

    /**
     * Adatok betöltése meglévő felhasználó esetén.
     */
    public void setUserData(User user) {
        this.editedUser = user;
        usernameField.setText(user.getUserName());
        emailField.setText(user.getEmail());
    }

    /**
     * Mentés gomb kezelése.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();

        if (username.isEmpty() || email.isEmpty()) {
            showAlert("Hiba", "Minden mezőt ki kell tölteni!");
            return;
        }

        if (editedUser == null) {
            // Új felhasználó
            User newUser = new User(username, email);
            userService.addUser(newUser);
        } else {
            // Meglévő felhasználó frissítése
            editedUser.setUserName(username);
            editedUser.setEmail(email);
            userService.updateUser(editedUser);
        }

        closeWindow();
    }

    /**
     * Mégse gomb kezelése.
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        closeWindow();
    }

    /**
     * Segédfüggvény az ablak bezárásához.
     */
    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Hibaüzenet megjelenítése.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
