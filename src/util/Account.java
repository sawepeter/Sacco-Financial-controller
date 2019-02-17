package util;

import java.sql.*;
import util.TrayNotify;
import util.Mysql_Connect;

public class Account {
 
    public String first_name = null;
    public String second_name = null;
    public String national_id =null;
    protected String Account_No =null;
    protected String password = null;
    protected double balance = 0.0;
    protected double deposit = 0.0;
    
    Connection connection = null;
    PreparedStatement preparedStatement  = null;
    ResultSet resultSet = null;
    
    TrayNotify notification = new TrayNotify();
    
    public Account(){
        connection = Mysql_Connect.connectdb();
    }
    
    
    //register an new customer
    public void register(String acc_no,String fname,String lname,String sname,String id, String gender) {
        
        try{
            String sql = "INSERT INTO customer( Account_ID, First_Name,Last_Name,Surname_Name,"
                    + "National_ID,Gender) values(?,?,?,?,?,?)";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acc_no);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setString(4, sname);
            preparedStatement.setString(5, id);
            preparedStatement.setString(6, gender);
            preparedStatement.executeQuery();
            
            notification.PopupAnimation("Registration", "Successfuly registered a new customer", "SUCCESS", 3);
         }
       
        catch(Exception e){
            notification.PopupAnimation("Registration", "An error occured Please try again", "ERROR", 3);
        }
        
    }
    //check balance
    protected void checkBalance(String id){
        this.national_id = id;
        
        try{
            
            Statement statement = connection.createStatement();
            String query ="SELECT First_Name,Account_ID,balance FROM account where National_ID="+id;
            ResultSet rs = statement.executeQuery(query);  
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
   
            Statement statement = connection.createStatement();
            String query ="UPDATE account SET balance = balance "+_depo+" where National_ID="+id;
            statement.executeQuery(query);
            
            notification.slideAnimation("Registration", "Successfuly deposited "+_depo, "SUCCESS", 2);
        }
        
        catch(Exception e){
            notification.PopupAnimation("Deposit", "An error occured in depositing "+_depo, "INFORMATION", 3);
        }
    }
    
    //withdraw
    protected void widthdraw(){
        
    }
    

}
