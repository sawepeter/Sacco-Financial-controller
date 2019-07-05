package util;

import java.sql.*;
import javax.swing.JOptionPane;
 
public class Mysql_Connect {
    
    Connection conn = null;
    public static Connection connectdb() {
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nakimo","root", "");
            return conn;
        }
        catch( Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
}
