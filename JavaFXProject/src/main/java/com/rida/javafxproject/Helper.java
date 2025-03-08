package com.rida.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;

public class Helper {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String qalam_id,boolean setMaximized) {
        Parent root = null;
        if (username != null && qalam_id != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Helper.class.getResource(fxmlFile));
                root = loader.load();
                UserpageController userpageController = loader.getController();
                userpageController.setUserInformation(username, qalam_id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Helper.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        if (setMaximized) {
            stage.setMaximized(true);
        }
        stage.setMaximized(true);
        stage.setScene(new Scene(root));

        stage.show();
    }
    public static void signupuser(ActionEvent event,String username,String qalam_id,String password) {
        PreparedStatement psInsert = null;
        PreparedStatement psUserExist = null;
        ResultSet resultset = null;

        try {
            psUserExist = Database.db.prepareStatement("SELECT * FROM users WHERE username=?");
            psUserExist.setString(1, username);
            resultset = psUserExist.executeQuery();

            if (resultset.isBeforeFirst()) {
                System.out.println("Username already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Try again this username is already taken");
                alert.show();
            } else {
                psInsert = Database.db.prepareStatement("Insert into users (username,qalam_id,password) VALUES (?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, qalam_id);
                psInsert.executeUpdate();
                changeScene(event, "userpage.fxml", "Welcome", username, qalam_id,true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUserExist != null) {
                try {
                    psUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void logInUser(ActionEvent event,String username,String password,String qalam_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            preparedStatement=Database.db.prepareStatement("SELECT username,password,qalam_id from users where username=?");
            preparedStatement.setString(1,username);
            resultset=preparedStatement.executeQuery();
            if(!resultset.isBeforeFirst()){
                System.out.println("User not found");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username and password");
                alert.show();
            }else{
                while(resultset.next()){

                    String retreivedpassword=resultset.getString("password");
                    String retreivedqalam_id=resultset.getString("qalam_id");
                    if(retreivedpassword.equals(password)){
                        changeScene(event,"userpage.fxml","Welcome",username,retreivedqalam_id,true);
                    }else{
                        System.out.println("Password did not match!");
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect.");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

