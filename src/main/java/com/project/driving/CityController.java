package com.project.driving;

import com.project.people.PeopleEntity;
import com.project.road.RoadServiceEntity;
import com.project.road.RoadServiceService;
import com.project.services.CityServiceEntity;
import com.project.services.CityServicesServ;
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
import java.util.List;

public class CityController {

    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    ListView<String> codeListview;
    @FXML
    ListView<String> costListView;
    @FXML
    ListView<String> desListview;
    @FXML
    Label displayView;

    CityServiceEntity cityServiceEntity = new CityServiceEntity();
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
        cityServiceEntity.setPeopleId(peopleEntityOld.getLicenseId());
        peopleEntity = peopleEntityOld;
    }


    @FXML
    public void onCodeSelected() throws IOException {
        if(codeListview.getSelectionModel().getSelectedItem() != null){
            CityServicesServ.getInstance().setCityCode(Integer.parseInt(codeListview.getSelectionModel().getSelectedItem()));
            CityServicesServ.getInstance().setCityPeopleId(peopleEntity.getLicenseId());
            System.out.println(CityServicesServ.getInstance().getCityCode());
            System.out.println(CityServicesServ.getInstance().getCityPeopleId());
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("city_popup.fxml"));
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
            List<CityServiceEntity> cityServiceEntities = CityServicesServ.getInstance().show();
            for (CityServiceEntity cityServiceEntity : cityServiceEntities){
                codeListview.getItems().add(String.valueOf(cityServiceEntity.getServiceCode()));
                costListView.getItems().add(String.valueOf(cityServiceEntity.getServiceCost()));
                desListview.getItems().add(cityServiceEntity.getServiceDes());
            }
        } catch (Exception e) {
            displayLabel1.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void payBtnPressed(){
        try {
            CityServicesServ.getInstance().insert(plateNoTxt.getText(),Integer.parseInt(carNoTxt.getText()),
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
