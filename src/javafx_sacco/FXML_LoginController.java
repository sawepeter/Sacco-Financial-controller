package javafx_sacco;

import util.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import util.Mysql_Connect;
/**
 *
 * @author Kenya
 */
public class FXML_LoginController implements Initializable {
    
   @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton login;
    
    Connection connection = null;
    PreparedStatement preparedStatement  = null;
    ResultSet resultSet = null;
    public static FXML_LoginController instance;
    String userName;
    boolean admin;
    
    public FXML_LoginController() {
        connection = Mysql_Connect.connectdb();
        instance = this;
    }
    public static FXML_LoginController getInstance() {
        return instance;
    }
    public String username(){
        return userName;
    }
    public boolean AdminPrivileges(){
        return admin;
    } 
    @FXML
    private void handleLoginAction(ActionEvent event) {
        
        Parent root;
        Stage home = new Stage();
        String textEmail = email.getText().toString();
        String textPassword = password.getText().toString();
        
        String sql = "SELECT * FROM employee WHERE Email = ? and Password =?";
        try{
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, textEmail);
            preparedStatement.setString(2, textPassword);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                infoBox("Invalid credentials Please Try Again",null,"Failed");
            }
            else{
                userName = resultSet.getString("First_Name");
                admin = resultSet.getBoolean("Admin");
                root = FXMLLoader.load(getClass().getResource("FXML_main.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                infoBox("Successfully Login", null ,"Success");
                login.getScene().getWindow().hide();
                home.setTitle("Nakimo Home");
                home.show();
                home.setResizable(false);
            }
        }
        catch( Exception e){
            //
            infoBox(e.toString(),null,"Error");
        }    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
    }    

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
