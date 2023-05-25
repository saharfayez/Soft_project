package soft_project;

public class Department_data {
   
   
   public int department_id;
    public String department_name;

    //  private Date from_date;
    //  private Date to_date;
    public Department_data(int department_id,
            String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;

    }

   
    
     public int getCode(){
        return department_id;
    }
   
    public  String getName(){
        return department_name;
    }
     public  void setname(String name){
        name=department_name;
    }

    
    
}
 


