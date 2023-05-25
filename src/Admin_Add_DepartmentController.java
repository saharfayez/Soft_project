/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

public class Admin_Add_DepartmentController implements Initializable {

     @FXML
     Button back_department_button;

     @FXML
     private TableView<Department_data> table;

     @FXML
     private TableColumn<Department_data, String>col_id;

     @FXML
     private TableColumn<Department_data, String>col_name ;

     @FXML
     TextField department_id_textfield, department_name_textfield;

     private ObservableList<Department_data> Dep_data_list;

     @Override
     public void initialize(URL url, ResourceBundle rb) {
        Departments_table();
     }

     private void Departments_table() {
         Dep_data_list = Methods.Departments_Data_table();
         for(int i = 0; i<Dep_data_list.size();i++ ){
            System.out.println(Dep_data_list.get(i));
         }
         col_id.setCellValueFactory(new PropertyValueFactory<Department_data,String>("code"));
         col_name.setCellValueFactory(new PropertyValueFactory<Department_data,String>("name"));
         table.setItems(Dep_data_list);
     }
    
     @FXML
     public void insert_data() {
        String id = (department_id_textfield.getText());
        String name = department_name_textfield.getText();
        Methods.insert_into_departments(id,name);
        Departments_table();
        clear();
     }
    
     @FXML
     public void back(){
         try {
            Stage  stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Soft_admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
         }   catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      back_department_button.getScene().getWindow().hide();
    }

    @FXML
    public void clear(){
        this.department_id_textfield.setText("");
        this.department_name_textfield.setText("");
    }

}
