/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import javax.swing.*;

import static soft_project.Stages.studentStage;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class Soft_studentController implements Initializable {

    List<CheckBox> checkBoxList = new ArrayList<>();

    @FXML
    private Button back;

    @FXML
    private Button assign;

    @FXML
    private Button view;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void assign() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Assign_subject.fxml"));
        Scene scene = new Scene(root);
        studentStage.setScene(scene);
        studentStage.show();
//        assign.getScene().getWindow().hide();
    }

    public void view() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View_subjects.fxml"));
        Scene scene = new Scene(root);
        studentStage.setScene(scene);
        studentStage.show();
        view.getScene().getWindow().hide();
    }


    public void back(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        back.getScene().getWindow().hide();
    }
    
}
