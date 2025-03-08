package com.rida.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LshapedPageController implements Initializable {

    @FXML
    private Button close_button;

    @FXML
    private Button help_button;

    @FXML
    private Button home_page;

    @FXML
    private Button profile_button;

    @FXML
    private Button double_bed_button;

    @FXML
    private Button bed_with_side_tables_button;

    @FXML
    private Button cupboard_button;

    @FXML
    private Button lamp_button;

    @FXML
    private Button led_tv_button;

    @FXML
    private Button l_shaped_sofa_button;

    @FXML
    private Button plant_button;

    @FXML
    private Button single_bed_button;

    @FXML
    private Button single_sofa_button;

    @FXML
    private Button double_sofa_button;

    @FXML
    private Button table_button;

    @FXML
    private AnchorPane roomPane;

    @FXML
    private Pane mainPane;

    // Map to hold furniture item paths
    private Map<Button, String> furnitureItems;

    // Reference to the currently selected ImageView
    private ImageView selectedImageView;

    // Maintain references to the resize and rotate handles
    private Rectangle resizeHandle;
    private Rectangle rotateHandle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_page.setOnAction(event -> Helper.changeScene(event, "Userpage.fxml", "Virtual Interior Designer", null, null, true));
        close_button.setOnAction(event -> Helper.changeScene(event, "design.fxml", "Virtual Interior Designer", null, null, true));
        help_button.setOnAction(event -> Helper.changeScene(event, "help.fxml", "Virtual Interior Designer", null, null, true));
        profile_button.setOnAction(event -> Helper.changeScene(event, "design.fxml", "Virtual Interior Designer", null, null, true));

        // Initialize the map with furniture item buttons and their respective paths
        furnitureItems = new HashMap<>();
        furnitureItems.put(double_bed_button, "bed.png");
        furnitureItems.put(bed_with_side_tables_button, "bedwithsidetables.png");
        furnitureItems.put(cupboard_button, "cubborad.png");
        furnitureItems.put(lamp_button, "lamp.png");
        furnitureItems.put(led_tv_button, "LED.png");
        furnitureItems.put(l_shaped_sofa_button, "LShaped.png");
        furnitureItems.put(plant_button, "plant.png");
        furnitureItems.put(single_bed_button, "singleBed.png");
        furnitureItems.put(single_sofa_button, "singlesofa.png");
        furnitureItems.put(double_sofa_button, "sofaicon.png");
        furnitureItems.put(table_button, "table.png");

        // Add action handlers for furniture item buttons
        for (Map.Entry<Button, String> entry : furnitureItems.entrySet()) {
            entry.getKey().setOnAction(event -> addImageToPane(entry.getValue()));
        }

        // Add event handler to delete selected furniture item on backspace or delete key press
        mainPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                if (selectedImageView != null) {
                    // Remove image
                    mainPane.getChildren().remove(selectedImageView);

                    // Remove resize and rotate handles
                    mainPane.getChildren().removeAll(resizeHandle, rotateHandle);

                    selectedImageView = null;
                    resizeHandle = null;
                    rotateHandle = null;
                }
            }
        });
    }

    private void addImageToPane(String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        enableDragResizeAndRotate(imageView);
        mainPane.getChildren().add(imageView);

        // Add click handler to select the ImageView
        imageView.setOnMouseClicked(event -> {
            if (selectedImageView != null) {
                selectedImageView.setStyle("");  // Remove selection style from previously selected image
            }
            selectedImageView = imageView;
            imageView.setStyle("-fx-effect: dropshadow(gaussian, blue, 10, 0, 0, 0);");  // Add a visual cue for selection
            mainPane.requestFocus();  // Make sure the pane has focus to receive key events
        });
    }

    private void enableDragResizeAndRotate(ImageView imageView) {
        final double[] dragDelta = new double[2];
        imageView.setOnMousePressed(event -> {
            dragDelta[0] = event.getSceneX() - imageView.getLayoutX();
            dragDelta[1] = event.getSceneY() - imageView.getLayoutY();
        });

        imageView.setOnMouseDragged(event -> {
            imageView.setLayoutX(event.getSceneX() - dragDelta[0]);
            imageView.setLayoutY(event.getSceneY() - dragDelta[1]);
        });

        // Create resize handle
        resizeHandle = new Rectangle(10, 10);
        resizeHandle.setStyle("-fx-fill: transparent; -fx-stroke: black;");
        resizeHandle.setOnMousePressed(event -> {
            dragDelta[0] = imageView.getFitWidth() - event.getSceneX();
            dragDelta[1] = imageView.getFitHeight() - event.getSceneY();
        });
        resizeHandle.setOnMouseDragged(event -> {
            imageView.setFitWidth(event.getSceneX() + dragDelta[0]);
            imageView.setFitHeight(event.getSceneY() + dragDelta[1]);
        });
        resizeHandle.layoutXProperty().bind(imageView.layoutXProperty().add(imageView.fitWidthProperty()).subtract(resizeHandle.getWidth() / 2));
        resizeHandle.layoutYProperty().bind(imageView.layoutYProperty().add(imageView.fitHeightProperty()).subtract(resizeHandle.getHeight() / 2));

        // Create rotate handle
        rotateHandle = new Rectangle(10, 10);
        rotateHandle.setStyle("-fx-fill: blue;");
        rotateHandle.setOnMousePressed(event -> {
            dragDelta[0] = event.getSceneX();
            dragDelta[1] = event.getSceneY();
        });
        rotateHandle.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - dragDelta[0];
            double deltaY = event.getSceneY() - dragDelta[1];
            double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
            imageView.getTransforms().add(new Rotate(angle * 0.01));
        });
        rotateHandle.layoutXProperty().bind(imageView.layoutXProperty().add(imageView.fitWidthProperty()).subtract(rotateHandle.getWidth() / 2));
        rotateHandle.layoutYProperty().bind(imageView.layoutYProperty().subtract(rotateHandle.getHeight() / 2));

        mainPane.getChildren().addAll(resizeHandle, rotateHandle);
    }
}
