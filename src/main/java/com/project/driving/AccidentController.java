package com.project.driving;

import com.project.accident.AccidentEntity;
import com.project.accident.AccidentRepository;
import com.project.accident.AccidentService;
import com.project.people.PeopleEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccidentController {

    Stage stage;
    Parent root;
    Scene scene;

    @FXML
    Label displayLabel;
    @FXML
    ListView<String> idListview;
    @FXML
    ListView<String> desListview;
    @FXML
    ListView<String> policeIdListview;
    @FXML
    ListView<String> timeListview;
    @FXML
    TextField carNumberAccidents;

    AccidentEntity accidentEntity = new AccidentEntity();
    PeopleEntity peopleEntity = new PeopleEntity();

    public void getInfoFromMain(PeopleEntity peopleEntityOld) throws Exception {
        peopleEntity = peopleEntityOld;
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

    @FXML
    public void findAccidents(){
        idListview.getItems().clear();
        desListview.getItems().clear();
        policeIdListview.getItems().clear();
        timeListview.getItems().clear();
        try {
            accidentEntity.setCarNo(Integer.parseInt(carNumberAccidents.getText()));
            if(AccidentService.getInstance().check(accidentEntity)){
                List<AccidentEntity> accidentEntities = AccidentService.getInstance().show(accidentEntity);
                for (AccidentEntity accident : accidentEntities){
                    idListview.getItems().add(String.valueOf(accident.getAccidentId()));
                    desListview.getItems().add(accident.getAccidentDes());
                    policeIdListview.getItems().add(String.valueOf(accident.getAccidentPoliceId()));
                    timeListview.getItems().add(String.valueOf(accident.getAccidentDate()));
                }
            }
            else {
                displayLabel.setText("Not a valid Car number");
            }
        }
        catch (NumberFormatException e){
            displayLabel.setText("Enter a number");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
