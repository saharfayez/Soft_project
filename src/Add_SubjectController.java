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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Add_SubjectController implements Initializable {

    @FXML
    private TextField code;

    @FXML
    private TextField name;

    @FXML
    private ComboBox departments;

    @FXML
    private VBox subjects;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private TableView<Subject> table;

    @FXML
    private TableColumn<Subject, String> code_col;

    @FXML
    private TableColumn<Subject, String> name_col;

    private ObservableList<Subject> subjectsList = FXCollections.observableArrayList();

    private List<CheckBox> subjectCheckBox = new ArrayList<>();

    private List<Department_data> departmentData = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Subjects_data();
        Departments();
        showSubjects();

        back.setOnAction(actionEvent -> {
            back();
        });

    }

    public void insertSubject()  {
        String code = this.code.getText();
        String name = this.name.getText();
        String department_name = (String) this.departments.getSelectionModel().getSelectedItem();

        System.out.println(department_name);

        Connection_to_database.connect();;
        Connection con = Connection_to_database.con;

        try {
            PreparedStatement statement = con.prepareStatement("insert into subjects(code, name, department_name)" +
                    " values(?,?,?)");

            statement.setString(1, code);
            statement.setString(2, name);
            statement.setString(3, department_name);

            statement.executeUpdate();

            PreparedStatement statement2 = con.prepareStatement("insert into subjects_pre values(?, ?)");
//
//
            for(int i = 0 ; i < subjectCheckBox.size(); i++){
                if (subjectCheckBox.get(i).isSelected()){
                    statement2.setString(1, name);
                    statement2.setString(2, subjectCheckBox.get(i).getText());
                    statement2.executeUpdate();
                }
            }

            clear();
            subjectsList.clear();
            Subjects_data();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void clear(){
        for(int i = 0 ; i < subjectCheckBox.size(); i++){
            subjectCheckBox.get(i).setSelected(false);
        }
        this.code.setText("");
        this.name.setText("");
        this.departments.getSelectionModel().clearSelection();
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

    private void showSubjects() {
        for(int i = 0 ; i < subjectsList.size(); i++){
            CheckBox checkBox = new CheckBox(subjectsList.get(i).getName());
            subjectCheckBox.add(checkBox);
            subjects.getChildren().add(checkBox);
        }
    }

    private void Subjects_data() {
        subjectsList = Methods.Subjects_data_table();
        code_col.setCellValueFactory(new PropertyValueFactory<Subject,String>("code"));
        name_col.setCellValueFactory(new PropertyValueFactory<Subject,String>("name"));
        table.setItems(subjectsList);
    }

    private void Departments() {
         departmentData = Methods.Departments_Data_table();
         for(int i = 0 ; i < departmentData.size() ; i++) {
             departments.getItems().add(departmentData.get(i).getName());
         }
    }
    
    public void delete() throws SQLException {    
        Subject selectedItem = table.getSelectionModel().getSelectedItem();
        Connection_to_database.connect();
        Connection con = Connection_to_database.con;
        try {        
            PreparedStatement statemente = con.prepareStatement("delete from subjects where name=?");
            statemente.setString(1, selectedItem.getName());        statemente.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "You can't delete, it will be deactivated instead");        
            PreparedStatement statemente = con.prepareStatement("update subjects set active = ? where name=?");
            statemente.setString(1, "0");        
            statemente.setString(2, selectedItem.getName());
            statemente.executeUpdate();        
        }
        Subjects_data();    
        subjects.getChildren().clear();
        showSubjects();}

}
