package com.project.driving;

import com.project.people.PeopleEntity;
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

import java.io.IOException;

public class MainPageController {

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    private AnchorPane mainPagePane;

    private PeopleEntity peopleEntity;

    public void getPeopleLoginInfo(PeopleEntity peopleEntityOld){
        peopleEntity = peopleEntityOld;
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


    public void switchToCityService(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("city_view.fxml"));
        fxmlLoader.load();
        CityController cityController = fxmlLoader.getController();
        cityController.getInfoFromMainPage(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("City Services");
        stage.show();
    }

    public void switchToRoadService(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("road_view.fxml"));
        fxmlLoader.load();
        RoadViewController roadViewController = fxmlLoader.getController();
        roadViewController.getInfoFromMainPage(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Road Services");
        stage.show();
    }

    public void switchToViolations(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("violation_view.fxml"));
        fxmlLoader.load();
        ViolationController violationController = fxmlLoader.getController();
        violationController.getInfoFromMain(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Violations");
        stage.show();
    }

    public void switchToAccidents(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("accident_view.fxml"));
        fxmlLoader.load();
        AccidentController accidentController = fxmlLoader.getController();
        accidentController.getInfoFromMain(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Accidents");
        stage.show();
    }

}
