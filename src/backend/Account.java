package backend;

import java.sql.*;

public class Account {
    String mysql_url = "jdbc:mysql://localhost:3306/sacco";
    String mysql_user = "root";
    String mysql_password = "bonoko1289";
    
    String first_name = null;
    String second_name = null;
    String national_id =null;
    String Account_No =null;
    String password = null;
    double balance = 0.0;
    double deposit = 0.0;
    
    Connection conn;
    
    //register an new customer
    protected void register(String fname,String lname,String pass,String id) {
        this.first_name = fname;
        this.second_name = lname;
        this.password = pass;
        this.national_id = id;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //mysql connection
            conn = DriverManager.getConnection(mysql_url,mysql_user, mysql_password);
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
             conn = DriverManager.getConnection(mysql_url,mysql_user, mysql_password);
            //connection object
            Statement smt = conn.createStatement();
            String query ="SELECT first_name,account_no,balance FROM account where national_id="+id;
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
    protected void deposit(String id,double _depo){
        this.national_id = id;
        this.deposit = _depo;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(mysql_url,mysql_user, mysql_password);
            Statement smt = conn.createStatement();
            String query ="UPDATE customer SET balance = balance where national_id="+id;
            smt.executeQuery(query);

        }
        catch(Exception e){
                e.printStackTrace();
        }
    }
    
    //withdraw
    protected void widthdraw(){
        
    }
    

}
