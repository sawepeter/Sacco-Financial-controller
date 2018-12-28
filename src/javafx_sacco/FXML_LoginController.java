package javafx_sacco;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import backend.*;
/**
 *
 * @author Kenya
 */
public class FXML_LoginController implements Initializable {
    
    employee x = new employee();
    
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    
    @FXML
    private void handleLoginAction(ActionEvent event) {
        String xemail = email.getText();
        String xpassword = password.getText();
        System.out.println(xemail);
        System.out.println(xpassword);
        x.login("\""+xemail+"\"", xpassword); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
