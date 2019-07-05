package util;

import java.sql.*;
import javax.swing.JOptionPane;

/*
    GIT
    -added comments
*/
public class Employee  {
    
    String fname,lname,sname,email,password,date,national_id;
    Connection conn;
    
    public  String user = null;
    public Boolean route = false;
    

    public Employee(){
        
    }
    

    public Employee(String f, String l, String s,String n,String d){
        this.fname = f;
        this.lname = l;
        this.sname = s;
        this.national_id = n;
        this.date = d;
    }
  

    void register(String fn,String ln,String sn,String ni,String da){
        
    }
    
   
    
}
