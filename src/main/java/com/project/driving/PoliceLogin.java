package com.project.driving;

import com.project.police.PoliceEntity;
import com.project.police.PoliceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PoliceLogin {

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    TextField policeIdTxt;
    @FXML
    PasswordField passwordTxt;
    @FXML
    TextField firstNameTxt;
    @FXML
    TextField LastNameTxt;
    @FXML
    Label displayLabel;

    @FXML
    public void onLoginBtnPressed(ActionEvent event){
        PoliceEntity policeEntity = new PoliceEntity();
        try {
            policeEntity.setPoliceId(Integer.parseInt(policeIdTxt.getText()));
            if(PoliceService.getInstance().check(policeEntity,firstNameTxt.getText(), LastNameTxt.getText()
                    ,passwordTxt.getText())){
                switchToPoliceMain(event,policeEntity);
            }
        }
        catch (NumberFormatException e){
            displayLabel.setText("please enter a number");
        }
        catch (Exception e){
            displayLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
        fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public void switchToPoliceMain(ActionEvent event,PoliceEntity policeEntity) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("police_main.fxml"));
        fxmlLoader.load();
        PoliceMainController policeMainController = fxmlLoader.getController();
        policeMainController.getInfoFromLogin(policeEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

}
