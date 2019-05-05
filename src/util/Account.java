package util;

import java.sql.*;
import util.TrayNotify;
import util.Mysql_Connect;
import java.sql.Date;

public class Account {
 
    public String first_name = null;
    public String second_name = null;
    public String national_id =null;
    public String Account_No =null;
    protected String password = null;
    public int balance = 0;
    protected double deposit = 0.0;
    
    Connection connection = null;
    PreparedStatement preparedStatement  = null;
    ResultSet resultSet = null;
    
    TrayNotify notification = new TrayNotify();
    
    public Account(){
        connection = Mysql_Connect.connectdb();
    }
    
    //register an new customer
    public void register(String fname,String lname,String sname,String id,int bal, String gender,String date, String picture) {
        
        try{
            String sql = "INSERT INTO account( First_Name,Last_Name,Surname_Name,"
                    + "National_ID, Balance,Gender,Birthdate,Picture) values(?,?,?,?,?,?,?,?)";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, sname);
            preparedStatement.setString(4, id);
            preparedStatement.setInt(5, bal);
            preparedStatement.setString(6, gender);
            preparedStatement.setDate(7, Date.valueOf(date));
            preparedStatement.setString(8, picture);
            
            //check for duplicates
            //todo
            preparedStatement.execute();
          
            notification.PopupAnimation("Registration", "Successfuly registered a new customer", "SUCCESS", 3);
         }
       
        catch(Exception e){
            notification.PopupAnimation("Registration", "An error occured Please try again", "ERROR", 3);
        }
        
    }
    //check balance
    public void checkBalance(String id){
        
        try{
            String query ="SELECT First_Name,Account_ID,Balance FROM account where National_ID= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
             
            while(resultSet.next()){
                
                first_name = resultSet.getString("First_Name");
                Account_No = resultSet.getString("Account_ID");
                balance = resultSet.getInt("Balance");
            }   
          }
        catch(Exception e){
          notification.PopupAnimation("Search", "An error occured Please try again", "INFORMATION", 3);
      }
    }
    
    //deposit
    public void deposit(String acc_no, int _depo){
        
        try{
            
            String query ="UPDATE account SET balance = balance + ? where Account_ID= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, _depo);
            preparedStatement.setString(2, acc_no);
            preparedStatement.execute();
            
            notification.fadeAnimation("Deposit", "Successfuly deposited "+_depo, "SUCCESS", 2);
        }
        
        catch(Exception e){
            notification.fadeAnimation("Deposit", "An error occured in depositing "+_depo, "INFORMATION", 3);
        }
    }
    
    //withdraw
    protected void widthdraw(){
        
    }

}
