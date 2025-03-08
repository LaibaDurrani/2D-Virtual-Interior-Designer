package com.rida.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Design2controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button close_button;

    @FXML
    private Button home_page;

    @FXML
    private SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "userpage.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
        close_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "inspiration.fxml", "Virtual Interior Designer", null, null, true);
            }
        });

    }
}
