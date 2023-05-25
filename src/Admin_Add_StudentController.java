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

public class Admin_Add_StudentController implements Initializable {

     @FXML
     Button back_student_button;
     @FXML
    private TableView<Students_data> student_table;

    @FXML
    private TableColumn<Students_data, Integer>student_id_col;

    @FXML
    private TableColumn<Students_data, String>student_name_col ;
    
      @FXML
    private TableColumn<Students_data, String>student_password_col ;
    
 
    @FXML
    TextField student_academic_number ,student_username ,student_password;
    
   
    
    
     private ObservableList<Students_data> list;
     
    public void Students_table() {

        list = Methods.Students_data_table();

        student_id_col.setCellValueFactory(new PropertyValueFactory<Students_data,Integer>("academicNumber"));
        student_name_col.setCellValueFactory(new PropertyValueFactory<Students_data,String>("name"));
        student_password_col.setCellValueFactory(new PropertyValueFactory<Students_data,String>("password"));
        student_table.setItems(list);
    }
    
    
       @FXML
    public void insert_data() {
        String id = student_academic_number.getText();
        String name = student_username.getText();
        String pass = student_password.getText();
        Methods.insert_into_students(id, name, pass);
        Students_table();
        clear();
    }
    
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
         back_student_button.getScene().getWindow().hide();
    }

    public void clear(){
        student_academic_number.setText("");
        student_username.setText("");
        student_password.setText("");
    }

    public void initialize(URL url, ResourceBundle rb) {
       Students_table();
    }    
    
}
