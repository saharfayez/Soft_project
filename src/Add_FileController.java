/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static soft_project.Stages.studentStage;
import static soft_project.Stages.teacherStage;

/**
 * FXML Controller class
 *
 * @author Alresala
 */
public class Add_FileController implements Initializable {

    @FXML
    private TextField subjectName;

    @FXML
    private Label fileName;

    @FXML
    private Button upload;

    @FXML
    private Button save;

    @FXML
    private Button back;

    private String name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        upload.setOnAction(event -> {
            fileUpload();
        });

        save.setOnAction(event -> {
            saveFile();
        });

        back.setOnAction(actionEvent -> {
            back();
        });

    }

    private void fileUpload() {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(teacherStage);
        if (file != null) {
            try {
                name = uploadFile(file, "server-url");
                fileName.setText(name);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private void saveFile()  {
        String name = this.subjectName.getText();
        Connection_to_database.connect();;
        Connection con = Connection_to_database.con;

        try {
            PreparedStatement statement = con.prepareStatement("insert into subjects_files (subject_name, file_url) values(?,?)");
            statement.setString(1, name);
            statement.setString(2, this.name);
            statement.executeUpdate();

            subjectName.setText("");
            fileName.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void back(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Soft_teacher.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        back.getScene().getWindow().hide();
    }

    private String uploadFile(File file, String url) throws IOException{
        return file.getName();
    }


}
