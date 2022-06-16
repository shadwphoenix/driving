package com.project.driving;

import com.project.accident.AccidentService;
import com.project.people.PeopleEntity;
import com.project.violation.ViolationEntity;
import com.project.violation.ViolationRepository;
import com.project.violation.ViolationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViolationController {

    Stage stage;
    Parent root;
    Scene scene;

    ViolationEntity violationEntity = new ViolationEntity();
    PeopleEntity peopleEntity = new PeopleEntity();

    @FXML
    Label violationPointLabel;
    @FXML
    TextField plateNumberViolation;
    @FXML
    ListView<String> idListview;
    @FXML
    ListView<String> desListview;
    @FXML
    ListView<String> pointListview;
    @FXML
    ListView<String> policeIdListview;
    @FXML
    ListView<String> chargeListview;
    @FXML
    ListView<String> timeListview;
    @FXML
    Label displayLabel;
    @FXML
    public Button payBtn;
    @FXML
    TextField cardNo;
    @FXML
    TextField password;
    @FXML
    public Label displayLabel1;


    public void getInfoFromMain(PeopleEntity peopleEntityOld) {
        violationEntity.setPeopleIdViolation(peopleEntityOld.getLicenseId());
        peopleEntity = peopleEntityOld;
    }


    public void findViolations(){
        idListview.getItems().clear();
        desListview.getItems().clear();
        policeIdListview.getItems().clear();
        pointListview.getItems().clear();
        chargeListview.getItems().clear();
        timeListview.getItems().clear();
        try {
            violationPointLabel.setText(String.valueOf(ViolationService.getInstance().points(violationEntity)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        violationEntity.setPlateNoViolation(plateNumberViolation.getText());
        try {
            if (ViolationService.getInstance().check(violationEntity)) {
                List<ViolationEntity> violationEntities = ViolationService.getInstance().show(violationEntity);
                for (ViolationEntity violationEntity : violationEntities) {
                    idListview.getItems().add(String.valueOf(violationEntity.getViolationId()));
                    desListview.getItems().add(violationEntity.getViolationDes());
                    pointListview.getItems().add(String.valueOf(violationEntity.getViolationPoint()));
                    policeIdListview.getItems().add(String.valueOf(violationEntity.getPoliceIdViolation()));
                    chargeListview.getItems().add(String.valueOf(violationEntity.getViolationCharge()));
                    timeListview.getItems().add(String.valueOf(violationEntity.getViolationDate()));
                }
            }
            else{
                displayLabel.setText("Invalid Plate");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void selectViolation() throws IOException {
        if (idListview.getSelectionModel().getSelectedItem() != null) {
            ViolationService.getInstance().setViolationID(Integer.parseInt(idListview.getSelectionModel().getSelectedItem()));
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("violation_popup.fxml"));
            fxmlLoader.load();
            root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.setTitle("payment");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(idListview.getScene().getWindow());
            stage.showAndWait();
        }
    }

    @FXML
    public void payBtnPressed(){
        try {
            ViolationService.getInstance().clear(cardNo.getText(),password.getText());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            displayLabel1.setText("Failed to clear violation");
        }catch (NumberFormatException e){
            displayLabel1.setText("Please enter number in cardNo and password");
        } catch (Exception e) {
            displayLabel1.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
        displayLabel1.setText("cleared successfully");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stage = (Stage) payBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("main_menu.fxml"));
        fxmlLoader.load();
        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.getPeopleLoginInfo(peopleEntity);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = fxmlLoader.getRoot();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }




}
