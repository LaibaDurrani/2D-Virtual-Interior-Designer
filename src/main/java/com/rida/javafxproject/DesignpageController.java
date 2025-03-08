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

public class DesignpageController implements Initializable {

    @FXML
    private Button Lshaped;

    @FXML
    private Button add_button;

    @FXML
    private Button close_button;

    @FXML
    private Button help_button;

    @FXML
    private Button home_page;

    @FXML
    private Button profile_button;

    @FXML
    private Button rectangle;

    @FXML
    private AnchorPane roomPane;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button square;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        square.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "square.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
        Lshaped.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "LShaped.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
        rectangle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "rectangle.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
        close_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "Userpage.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
        home_page.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Helper.changeScene(event, "Userpage.fxml", "Virtual Interior Designer", null, null, true);
            }
        });
    }
}
