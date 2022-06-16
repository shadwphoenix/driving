package com.project.driving;

import com.project.police.PoliceEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PoliceMainController {

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    private AnchorPane mainPagePane;

    private PoliceEntity policeEntity;

    public void getInfoFromLogin(PoliceEntity policeEntityOld){
        policeEntity = policeEntityOld;
    }

    @FXML
    public void switchToViolations(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("police_violation.fxml"));
        fxmlLoader.load();
        PoliceViolationController policeViolationController = fxmlLoader.getController();
        policeViolationController.getInfoFromMain(policeEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Violations");
        stage.show();
    }

    @FXML
    public void switchToAccidents(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("police_accident.fxml"));
        fxmlLoader.load();
        PoliceAccidentController policeAccidentController = fxmlLoader.getController();
        policeAccidentController.getInfoFromMain(policeEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Violations");
        stage.show();
    }

    @FXML
    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("you are logging out");
        alert.setContentText("Do you want to save?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) mainPagePane.getScene().getWindow();
            System.out.println("closing");
            stage.close();
        }
    }
}
