package backend;

import java.sql.*;

public class Account {
    
    public String first_name = null,second_name = null,national_id =null;
    protected String Account_No =null ,password = null;
    protected double balance = 0.0,deposit = 0.0;
    Connection conn;
    
    //register an new customer
    protected void register(String fname,String lname,String pass,String id){
        this.first_name = fname;
        this.second_name = lname;
        this.password = pass;
        this.national_id = id;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //mysql connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco","root", "bonoko1289");
            //connection object
            Statement smt = conn.createStatement();
            String query ="INSERT INTO customer values("+fname+""+lname+""+pass+""+id+""+")";
          
            smt.executeQuery(query);      
         }
       
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //check balance
    protected void checkBalance(String id){
        this.national_id = id;
      
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //mysql connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco","root", "bonoko1289");
            //connection object
            Statement smt = conn.createStatement();
            String query ="SELECT first_name,account_no,balance FROM empl where national_id="+id;
            ResultSet rs = smt.executeQuery(query);  
            while(rs.next()){
                
                first_name = rs.getString("first_name");
                Account_No = rs.getString("account_no");
                balance = rs.getDouble("balance");
          
            }   
          }
        catch(Exception e){
          e.printStackTrace();
      }
    }
    
    //deposit
    protected void deposit(){
        
    }
    
    //withdraw
    protected void widthdraw(){
        
    }
    
    //
}
