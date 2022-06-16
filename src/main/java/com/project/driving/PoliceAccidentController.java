package com.project.driving;

import com.project.accident.AccidentEntity;
import com.project.accident.AccidentService;
import com.project.police.PoliceEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PoliceAccidentController {

    Stage stage;
    Parent root;
    Scene scene;

    @FXML
    Label displayLabel;
    @FXML
    TextField carNoTxt;
    @FXML
    TextField accidentTxt;
    @FXML
    TextField accidentTimeTxt;

    private PoliceEntity policeEntity;

    public void getInfoFromMain(PoliceEntity policeEntityOld){
        policeEntity = policeEntityOld;
    }

    @FXML
    public void registerBtn(){
        AccidentEntity accidentEntity = new AccidentEntity();
        accidentEntity.setAccidentPoliceId(policeEntity.getPoliceId());
        accidentEntity.setAccidentDes(accidentTxt.getText());
        try{
            accidentEntity.setCarNo(Integer.parseInt(carNoTxt.getText()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            accidentEntity.setAccidentDate(formatter.parse(accidentTimeTxt.getText()));
            AccidentService.getInstance().add(accidentEntity);
        }catch (NumberFormatException e) {
            displayLabel.setText("Enter a Number in Number field");
        }catch (ParseException e) {
            displayLabel.setText("Date format is not valid");
        } catch (Exception e) {
            displayLabel.setText("Failed to add to accident");
            System.out.println(e.getMessage());
        }
        displayLabel.setText("Accident added successfully");
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
