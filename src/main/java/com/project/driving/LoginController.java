package com.project.driving;

import com.project.people.PeopleEntity;
import com.project.people.PeopleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    public TextField licenseIdTxtField;
    @FXML
    public TextField firstNameTxtField;
    @FXML
    public TextField lastNameTxtField;
    @FXML
    public Label displayLabel;

    @FXML
    public void pressLoginBtn(ActionEvent event) {

        PeopleEntity peopleEntity = new PeopleEntity();
        try {
            peopleEntity.setLicenseId(Integer.parseInt(licenseIdTxtField.getText()));
            if(PeopleService.getInstance().check(peopleEntity,firstNameTxtField.getText(),lastNameTxtField.getText()))
                switchToMainPage(event, peopleEntity);
        }
        catch (NumberFormatException e){
            displayLabel.setText("Enter a number please");
        } catch (Exception e){
            displayLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void switchToPolice(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("police_login.fxml"));
        fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Police Login");
        stage.show();
    }

    public void switchToMainPage(ActionEvent event,PeopleEntity peopleEntity) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("main_menu.fxml"));
        fxmlLoader.load();
        MainPageController mainPageController =fxmlLoader.getController();
        mainPageController.getPeopleLoginInfo(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }

}