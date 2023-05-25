package soft_project;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import static soft_project.Methods.con;
import static soft_project.Stages.adminStage;
import static soft_project.Stages.studentStage;
import static soft_project.Stages.teacherStage;

public class FXMLDocumentController implements Initializable {

   
   
    @FXML
    ComboBox<String> combo;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imageview2;
    @FXML
    private TextField t1, t2;
    @FXML
    Button btn;

    public static java.sql.Connection con = Connection_to_database.con;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException, SQLException {

        Connection_to_database.connect();
        String name = t1.getText();

        String pass = t2.getText();

        if (name.equals("") && pass.equals("")) {

            JOptionPane.showMessageDialog(null, "please fill the username and password");

        } else {

           ResultSet loginInUser = Methods.login(name, pass, combo.getValue());
            if (Methods.flag == 1) {

                Parent root = FXMLLoader.load(getClass().getResource("Soft_admin.fxml"));    Scene scene = new Scene(root);
                adminStage.setScene(scene);    
                adminStage.show();
                btn.getScene().getWindow().hide();
            } else if (Methods.flag == 2) {
                while (loginInUser.next()){       
                    LoggedInUser.academicNumber = loginInUser.getString("academic_number");
                    LoggedInUser.username = loginInUser.getString("username");    
                }
                Parent root = FXMLLoader.load(getClass().getResource("Soft_teacher.fxml"));
                Scene scene = new Scene(root);    teacherStage.setScene(scene);
                teacherStage.show();   
                btn.getScene().getWindow().hide();
                } else if (Methods.flag == 3) {
                     while (loginInUser.next()){       
                         LoggedInUser.academicNumber = loginInUser.getString("academic_number");
                         LoggedInUser.username = loginInUser.getString("username");   
                     }

                    Parent root = FXMLLoader.load(getClass().getResource("Soft_student.fxml"));    
                    Scene scene = new Scene(root);
                    studentStage.setScene(scene);    
                    studentStage.show();
                    btn.getScene().getWindow().hide();
}

//            if (Methods.flag == 1) {
//
//                Parent root = FXMLLoader.load(getClass().getResource("Soft_admin.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//                btn.getScene().getWindow().hide();
//
//            } else if (Methods.flag == 2) {
//
//                Parent root = FXMLLoader.load(getClass().getResource("Soft_teacher.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//                btn.getScene().getWindow().hide();
//
//            }
//            if (Methods.flag == 3) {
//
//                Parent root = FXMLLoader.load(getClass().getResource("Soft_student.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//                btn.getScene().getWindow().hide();
//
//            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo.setItems(FXCollections.observableArrayList("Admin", "Teacher", "Student"));
    }

}
