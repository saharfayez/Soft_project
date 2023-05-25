/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;

import static javafx.fxml.FXMLLoader.load;
import static soft_project.Stages.studentStage;
import static soft_project.Stages.teacherStage;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class Soft_teacherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scroll;

    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

    @FXML
    private TableView<Subject> table;

    @FXML
    private TableColumn<Subject, String> id_col;

    @FXML
    private TableColumn<Subject, String> name_col;

    @FXML
    private Button upload;

    @FXML
    private Button back;

    @FXML
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(LoggedInUser.username);
        System.out.println(LoggedInUser.academicNumber);


        showSubjects();
        upload.setOnAction(event -> {
            Parent root = null;
            try {
                root = load(getClass().getResource("Add_file.fxml"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            Scene scene = new Scene(root);
            studentStage.setScene(scene);
            studentStage.show();
            upload.getScene().getWindow().hide();
        });
    }

    private String uploadFile(File file, String url) throws IOException{
        return file.getName();
    }

    private void showSubjects() {
        getSubjectsFromDatabase();
        for (int i = 0; i < subjects.size(); i++){
            System.out.println(subjects.get(i));
        }

        id_col.setCellValueFactory(new PropertyValueFactory<>("code"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(subjects);

    }

    private void getSubjectsFromDatabase() {
        Connection_to_database.connect();
        Connection con = Connection_to_database.con;


        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from subjects where teacher_code= " +LoggedInUser.academicNumber );
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Subject subject = new Subject(resultSet.getString("code"),
                resultSet.getString("name"));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
