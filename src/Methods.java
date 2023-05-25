package soft_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

public class Methods {

    private static PreparedStatement state;
    private static ResultSet rs;
    public static java.sql.Connection con = Connection_to_database.con;
    static int flag = 0;
    static String username_admin, password_admin, username_teacher, password_teacher, username_student, password_student;
    FXMLDocumentController cont;

    public static ResultSet login(String name, String pass, String value) {

        try {
            Connection_to_database.connect();
            String queryAdmin = "SELECT * FROM admins WHERE username = ? AND password = ?";
            PreparedStatement stmtAdmin = con.prepareStatement(queryAdmin);
            stmtAdmin.setString(1, name);
            stmtAdmin.setString(2, pass);
            ResultSet rsAdmin = stmtAdmin.executeQuery();

            String queryManager = "SELECT * FROM teachers WHERE username = ? AND password = ?";
            PreparedStatement stmtManager = con.prepareStatement(queryManager);
            stmtManager.setString(1, name);
            stmtManager.setString(2, pass);
            ResultSet rsTeacher = stmtManager.executeQuery();

            String queryEmployee = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement stmtEmployee = con.prepareStatement(queryEmployee);
            stmtEmployee.setString(1, name);
            stmtEmployee.setString(2, pass);
            ResultSet rsStudent = stmtEmployee.executeQuery();

            if (rsAdmin.next() && value.equals("Admin")) {
                System.out.println("admin");
                flag = 1;
               return rsAdmin;
            } else if (rsTeacher.next() && value.equals("Teacher")) {
                System.out.println("teacher");
                flag = 2;
                rsTeacher.beforeFirst();
                return rsTeacher;
            } else if (rsStudent.next() && value.equals("Student")) {

                System.out.println("student");
                flag = 3;
                rsStudent.beforeFirst();
                return rsStudent;
            } else {
                JOptionPane.showMessageDialog(null, "username or password is wrong");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;

    }

     public static ObservableList<Department_data> Departments_Data_table() {

        ObservableList<Department_data> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM departments";
        Connection_to_database.connect();

        try {
            state = con.prepareStatement(sql);
            rs = state.executeQuery();
            Department_data Dep_data;
            while (rs.next()) {
                Dep_data= new Department_data(Integer.parseInt(rs.getString("code")),
                        rs.getString("name")
                );
                listData.add(Dep_data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listData;
    }
     
   public static ObservableList<Students_data> Students_data_table() {
        ObservableList<Students_data> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM students";
        Connection_to_database.connect();

        try {
            state = con.prepareStatement(sql);
            rs = state.executeQuery();
            Students_data stu_data;

            while (rs.next()) {
                stu_data= new Students_data(Integer.parseInt(rs.getString("academic_number")),
                        rs.getString("username"),
                         rs.getString("password")
                );

                listData.add(stu_data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listData;
    }


    public static ObservableList<Teacher> Teachers_data_table() {

        ObservableList<Teacher> listData = FXCollections.observableArrayList();

        Connection_to_database.connect();
        Connection con = Connection_to_database.con;


        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from teachers");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Teacher teacher = new Teacher(resultSet.getString("academic_number"),
                        resultSet.getString("username"));
                listData.add(teacher);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());        }
        return listData;
    }

    public static ObservableList<Subject> Subjects_data_table() {

        ObservableList<Subject> listData = FXCollections.observableArrayList();

        Connection_to_database.connect();
        Connection con = Connection_to_database.con;


        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from subjects where active =1");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Subject subject = new Subject(resultSet.getString("code"),
                        resultSet.getString("name"));
                listData.add(subject);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listData;
    }


     public static void insert_into_departments(String idDepartment , String nameDepatment) {
        Connection_to_database.connect();

        try {
            PreparedStatement state = con.prepareStatement("insert into departments values(?,?, ?)");
            
            state.setString(1, idDepartment);
            state.setString(2, nameDepatment);
            state.setString(3, "1");
            state.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

      public static void insert_into_students(String id , String name , String pass) {
        Connection_to_database.connect();

        try {
            PreparedStatement state = con.prepareStatement("insert into students(academic_number,username,password) values(?,?,?)");
            state.setString(1, id);
            state.setString(2, name);
            state.setString(3, pass);

            state.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
