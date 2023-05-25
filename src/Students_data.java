/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soft_project;

public class Students_data {
   
   
    public int academicNumber;
    public String name;
    public String password;


    
    public Students_data(int academicNumber ,
            String name , String password) {
        
        
        
        this.academicNumber = academicNumber;
        this.name = name;
        
        this.password=password;

    }

   
    
     public int getAcademicNumber(){
        return academicNumber;
    }
   
    public  String getName(){
        return name;
    }
     public  String getPassword(){
        return password;
    }

    
    
}