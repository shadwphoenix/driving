package com.project.driving;

import com.project.police.PoliceEntity;
import com.project.violation.ViolationEntity;
import com.project.violation.ViolationService;
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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PoliceViolationController {

    PoliceEntity policeEntity = new PoliceEntity();

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    Label displayLabel;
    @FXML
    TextField violationDesText;
    @FXML
    TextField pointText;
    @FXML
    TextField plateText;
    @FXML
    TextField peopleIdText;
    @FXML
    TextField chargeText;
    @FXML
    TextField timeText;



    public void getInfoFromMain(PoliceEntity policeEntityOld){
        policeEntity = policeEntityOld;
    }



    @FXML
    public void registerBtn(){
        ViolationEntity violationEntity = new ViolationEntity();
        violationEntity.setPoliceIdViolation(policeEntity.getPoliceId());
        System.out.println(violationEntity.getPoliceIdViolation());
        violationEntity.setViolationDes(violationDesText.getText());
        violationEntity.setPlateNoViolation(plateText.getText());
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            violationEntity.setViolationDate(formatter.parse(timeText.getText()));
            violationEntity.setViolationPoint(Integer.parseInt(pointText.getText()));
            violationEntity.setPeopleIdViolation(Integer.parseInt(peopleIdText.getText()));
            violationEntity.setViolationCharge(Integer.parseInt(chargeText.getText()));
            ViolationService.getInstance().add(violationEntity);
            displayLabel.setText("Successfully added");
        }
        catch (NumberFormatException e){
            displayLabel.setText("Enter a number in Number fields");
        } catch (ParseException e) {
            displayLabel.setText("Incorrect Date");
        } catch (Exception e) {
            displayLabel.setText("Failed to add violation");
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void goBackToMainPolice(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("police_main.fxml"));
        fxmlLoader.load();
        PoliceMainController policeMainController = fxmlLoader.getController();
        policeMainController.getInfoFromLogin(policeEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }
}
