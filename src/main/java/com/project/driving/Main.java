package com.project.driving;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            logout(stage);
            event.consume();
        });
    }

    private void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("you are logging out");
        alert.setContentText("Do you want to save?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("closing");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}