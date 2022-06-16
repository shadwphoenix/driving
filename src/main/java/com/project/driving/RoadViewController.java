package com.project.driving;

import com.project.people.PeopleEntity;
import com.project.road.RoadServiceEntity;
import com.project.road.RoadServiceService;
import com.project.violation.ViolationEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoadViewController {

    @FXML
    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    ListView<String> codeListview;
    @FXML
    ListView<String> costListView;
    @FXML
    ListView<String> desListview;

    RoadServiceEntity roadServiceEntity = new RoadServiceEntity();
    PeopleEntity peopleEntity = new PeopleEntity();

    @FXML
    Button payBtn;
    @FXML
    TextField cardNo;
    @FXML
    TextField password;
    @FXML
    Label displayLabel1;
    @FXML
    TextField plateNoTxt;
    @FXML
    TextField carNoTxt;

    public void getInfoFromMainPage(PeopleEntity peopleEntityOld){
        roadServiceEntity.setPeopleId(peopleEntityOld.getLicenseId());
        peopleEntity = peopleEntityOld;
    }


    @FXML
    public void onCodeSelected() throws IOException {
        if(codeListview.getSelectionModel().getSelectedItem() != null){
            RoadServiceService.getInstance().setRoadCode(Integer.parseInt(codeListview.getSelectionModel().getSelectedItem()));
            RoadServiceService.getInstance().setRoadPeopleId(peopleEntity.getLicenseId());
            System.out.println(RoadServiceService.getInstance().getRoadCode());
            System.out.println(RoadServiceService.getInstance().getRoadPeopleId());
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("road_popup.fxml"));
            fxmlLoader.load();
            root = fxmlLoader.getRoot();
            stage.setScene(new Scene(root));
            stage.setTitle("payment");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(codeListview.getScene().getWindow());
            stage.showAndWait();
        }
    }

    @FXML
    public void BtnPressed(){
        try {
            List<RoadServiceEntity> roadServiceEntities = RoadServiceService.getInstance().show();
            for (RoadServiceEntity roadService : roadServiceEntities){
                codeListview.getItems().add(String.valueOf(roadService.getRoadCode()));
                costListView.getItems().add(String.valueOf(roadService.getRoadCost()));
                desListview.getItems().add(roadService.getDesRoad());
            }
        } catch (Exception e) {
            displayLabel1.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void payBtnPressed(){
        try {
            RoadServiceService.getInstance().insert(plateNoTxt.getText(), Integer.parseInt(carNoTxt.getText()),
                    Integer.parseInt(cardNo.getText()),Integer.parseInt(password.getText()));
        } catch (Exception e) {
            displayLabel1.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
        displayLabel1.setText("added successfully");
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
