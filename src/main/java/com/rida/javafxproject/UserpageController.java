package com.rida.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class UserpageController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label logged_stat;
    @FXML
    private Button ins_button;
    @Override
    public void initialize (URL location, ResourceBundle resources){
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"design.fxml","Virtual Interior Designer",null,null,true);
            }
        });
        ins_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"inspiration.fxml","Virtual Interior Designer",null,null,true);
            }
        });

    }
    public void setUserInformation(String username,String qalam_id){
        logged_stat.setText("Welcome "+username);
    }
}
