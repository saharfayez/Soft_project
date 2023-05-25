
package soft_project;

import java.sql.DriverManager;
import java.sql.*;
 
public class Connection_to_database {
     public static  java.sql.Connection con;
    public static  void connect(){
      
    try{
    Class.forName("com.mysql.jdbc.Driver");
  con=DriverManager.getConnection("jdbc:mysql://localhost/software_project" , "root", "");
   
    }
    catch(Exception e){
        System.out.println("error");
       e.printStackTrace();    
    }
    
    
    
    
    }
    
    
    
}
