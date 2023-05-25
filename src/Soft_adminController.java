package soft_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class Soft_adminController implements Initializable {

    @FXML
    Button add_department_button ,
            add_subject_button,
            attendance_button,
            add_teacher_button,
            add_student_button,back_button;

    
    @FXML
    public void add_department(ActionEvent event) throws IOException{
    
    Parent root = FXMLLoader.load(getClass().getResource("Admin_Add_Department.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                add_department_button.getScene().getWindow().hide();
    }

    @FXML
    public void add_student(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("Admin_Add_Student.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        add_student_button.getScene().getWindow().hide();

    }

    @FXML
    public void add_teacher(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("Add_teacher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        add_teacher_button.getScene().getWindow().hide();
    }

    @FXML
    public void add_subject(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("Add_subject.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        add_subject_button.getScene().getWindow().hide();
    }


     @FXML
    public void back(){
         try {
            Stage  stage = new Stage();
             Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

         }   catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }
         back_button.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
