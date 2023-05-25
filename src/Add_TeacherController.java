/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class Add_TeacherController implements Initializable {

    @FXML
    private TextField academic_number;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button add;

    @FXML
    private VBox subjects;

    @FXML
    private Button back;


    private ObservableList<Teacher> teachers ;

    @FXML
    private TableView<Teacher> table;

    @FXML
    private TableColumn<Teacher, String> academic_number_col;

    @FXML
    private TableColumn<Teacher, String> username_col;

    private List<Subject> subjectsList = new ArrayList<>();

    private List<CheckBox> checkBoxes = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showTeachers();
        showSubjects();

        add.setOnAction(event -> {
            addTeacher();
        });

        back.setOnAction(actionEvent -> {
            back();
        });

    }

    private void addTeacher()  {
        String academicNumber = this.academic_number.getText();
        String username = this.username.getText();
        String password = this.password.getText();

        Connection_to_database.connect();;
        Connection con = Connection_to_database.con;

        try {
            PreparedStatement statement = con.prepareStatement("insert into teachers values(?,?,?)");
            statement.setString(1, academicNumber);
            statement.setString(2, username);
            statement.setString(3, password);

            statement.executeUpdate();

            PreparedStatement statement2 = con.prepareStatement("update subjects set teacher_code= ? where name=?");


            for(int i = 0 ; i < checkBoxes.size(); i++){
                if (checkBoxes.get(i).isSelected()){
                    statement2.setString(1, academicNumber);
                    statement2.setString(2, checkBoxes.get(i).getText());
                    statement2.executeUpdate();
                }
            }

            clear();
            teachers.clear();
            showTeachers();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void showSubjects() {
        subjectsList = Methods.Subjects_data_table();
        for(int i = 0 ; i < subjectsList.size(); i++){
            CheckBox checkBox = new CheckBox(subjectsList.get(i).getName());
            checkBoxes.add(checkBox);
            subjects.getChildren().add(checkBox);
        }
    }

    private void showTeachers() {
        teachers = Methods.Teachers_data_table();
        for (int i = 0; i < teachers.size(); i++){
            System.out.println(teachers.get(i));
        }
        academic_number_col.setCellValueFactory(new PropertyValueFactory<>("academicNumber"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        table.setItems(teachers);
    }


    public void clear(){
        for(int i = 0 ; i < checkBoxes.size(); i++){
            checkBoxes.get(i).setSelected(false);
        }
        this.academic_number.setText("");
        this.username.setText("");
        this.password.setText("");
    }

    public void back(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Soft_admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        back.getScene().getWindow().hide();
    }

}
