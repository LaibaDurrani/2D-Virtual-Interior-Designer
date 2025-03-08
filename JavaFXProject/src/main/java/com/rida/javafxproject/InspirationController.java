package com.rida.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class InspirationController implements Initializable  {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button close_button;

    @FXML
    private Button design1;

    @FXML
    private Button design2;

    @FXML
    private Button home_page;

    @FXML
    private ImageView image_test;

    @FXML
    private ImageView img_add1;

    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        home_page.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"userpage.fxml","Virtual Interior Designer",null,null,true);
            }
        });
        close_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"login.fxml","Virtual Interior Designer",null,null,true);
            }
        });
        design1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"design1.fxml","Virtual Interior Designer",null,null,true);
            }
        });
        design2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event,"design2.fxml","Virtual Interior Designer",null,null,true);
            }
        });
    }
}
