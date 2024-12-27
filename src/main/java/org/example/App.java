package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  @Override
   public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user_view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Felhasználoi rendszer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
