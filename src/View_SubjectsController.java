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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;
import static soft_project.Stages.studentStage;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class View_SubjectsController implements Initializable {


    private ObservableList<SubjectFile> subjects = FXCollections.observableArrayList();

    @FXML
    private TableView<SubjectFile> table;

    @FXML
    private TableColumn<SubjectFile, String> name_col;

    @FXML
    private TableColumn<SubjectFile, String> file_col;

    @FXML
    private Button back;

    @FXML
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getSubjectFiles();
        name_col.setCellValueFactory(new PropertyValueFactory<SubjectFile,String>("name"));
        file_col.setCellValueFactory(new PropertyValueFactory<SubjectFile,String>("file"));
        table.setItems(subjects);
    }

    private void getSubjectFiles() {

        Connection_to_database.connect();
        Connection con = Connection_to_database.con;


        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from student_subjects ss, subjects_files sf " +
                    "where ss.student_code = ?" +
                    "and ss.subject_name = sf.subject_name;");
            preparedStatement.setString(1, LoggedInUser.academicNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                SubjectFile subjectFile = new SubjectFile(resultSet.getString("subject_name"),
                        resultSet.getString("file_url"));
                subjects.add(subjectFile);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
