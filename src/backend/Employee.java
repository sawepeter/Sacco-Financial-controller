package backend;

import java.sql.*;

public class Employee  {
    
    String fname,lname,sname,email,password,date,national_id;
   
    Connection conn;
    public Employee(){
        
    }
    
    public Employee(String f, String l, String s,String n,String d){
        this.fname = f;
        this.lname = l;
        this.sname = s;
        this.national_id = n;
        this.date = d;
    }
    public  String user = null;
    public Boolean route = false;
    public void login(String em, String ps){
        
        this.email = em;
        this.password = ps;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            //mysql connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco","root", "bonoko1289");

            //connection object
            Statement smt = conn.createStatement();
            String query ="SELECT first_name,email,password FROM empl where email="+em;
          
            ResultSet rs = smt.executeQuery(query);  
            while(rs.next()){
           
                if (ps.equals(rs.getString("password"))){
                    route = true;
                    System.out.println(route);
                    user = rs.getString("first_name"); 
                }
                else{
                    System.out.println("invalid details");
                } 
            }   
            
          }
        catch(Exception e){
          e.printStackTrace();
      }
    }
    
    
    void register(String fn,String ln,String sn,String ni,String da){
        
    }
    
}
