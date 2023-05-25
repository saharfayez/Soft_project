/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class Assign_SubjectController implements Initializable {

    List<CheckBox> checkBoxList = new ArrayList<>();

    private ObservableList<Subject> subjectsList = FXCollections.observableArrayList();


    @FXML
    private Button back;

    @FXML
    private VBox subjects;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSubjects();
    }


    public void save() {
        Connection_to_database.connect();
        Connection con = Connection_to_database.con;

        try {
            PreparedStatement preparedStatement =
                    con.prepareStatement("insert into student_subjects values(?, ?, ?);");

            for(int i = 0; i < checkBoxList.size() ; i++){
                if(checkBoxList.get(i).isSelected()){
                    preparedStatement.setString(1, checkBoxList.get(i).getText());
                    preparedStatement.setString(2, LoggedInUser.academicNumber);
                    preparedStatement.setString(3, "0");
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        clear();
    }


    private void showSubjects() {
        getSubjectsFromDatabase();
        for (int i = 0; i < subjectsList.size(); i++){
            System.out.println(subjectsList.get(i));
        }

        for(int i = 0 ; i < subjectsList.size(); i++){
            System.out.println(subjectsList.size());
            CheckBox checkBox = new CheckBox(subjectsList.get(i).getName());
            checkBoxList.add(checkBox);
            subjects.getChildren().add(checkBox);
        }
    }

    private void getSubjectsFromDatabase() {
        Connection_to_database.connect();
        Connection con = Connection_to_database.con;


        try {
            PreparedStatement preparedStatement =
                    con.prepareStatement("SELECT s.code, s.name FROM subjects s Where s.active = 1 And NOT EXISTS ( SELECT 1 FROM student_subjects ss WHERE ss.student_code = ? AND ss.subject_name = s.name) AND NOT EXISTS ( SELECT 1 FROM subjects_pre sp WHERE sp.subject_name = s.name AND NOT EXISTS ( SELECT 1 FROM student_subjects ss2 WHERE ss2.student_code = ? AND ss2.subject_name = sp.pre_name AND ss2.status = 1)); ");

            preparedStatement.setString(1, LoggedInUser.academicNumber);
            preparedStatement.setString(2, LoggedInUser.academicNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Subject subject = new Subject(resultSet.getString("code"),
                        resultSet.getString("name"));
                subjectsList.add(subject);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void back(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Soft_student.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        back.getScene().getWindow().hide();
    }

    public void clear(){
        for(int i = 0 ; i < checkBoxList.size(); i++){
            checkBoxList.get(i).setSelected(false);
        }
    }


}
