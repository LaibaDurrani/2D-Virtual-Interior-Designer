package com.rida.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MySavedProjects implements Initializable {

    @FXML
    private ImageView savedImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSavedSnapshot();
    }

    private void loadSavedSnapshot() {
        String url = "jdbc:mysql://localhost:3306/seekerslegacy";
        String username = "root";
        String password = "laiba406";

        String query = "SELECT image_path FROM SavedProjects ORDER BY id DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String imagePath = rs.getString("image_path");
                Image image = new Image(imagePath);
                savedImageView.setImage(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
