package javafx_sacco;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javafx_sacco.FXML_LoginController.infoBox;
import util.TrayNotify;
import util.Account;
import java.sql.*;
import javafx.scene.control.Tab;
import util.Mysql_Connect;

public class FXML_mainController implements Initializable {

    @FXML
    private AnchorPane MainWindow;
    @FXML
    private Label User_name;
    @FXML
    private ImageView clientPassport;
    @FXML
    private JFXButton btnImagePicker;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXDatePicker txtDateOfBirth;
    @FXML
    private JFXRadioButton radioMale;
    @FXML
    private JFXRadioButton radioFemale;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtIdNumber;
    @FXML
    private TextField txtSurnameName;
    @FXML
    private JFXTextField searchID;
    @FXML
    private TextField searchName;
    @FXML
    private TextField searchAccNo;
    @FXML
    private TextField searchBal;
    @FXML
    private TextField depositAccNo;
    @FXML
    private TextField depositAmount;
    @FXML
    private TextField withdrawAccNo;
    @FXML
    private TextField withdrawAmount;
    @FXML
    private PasswordField withdrawID;
    @FXML
    private TableView<?> TableView;
    @FXML
    private TableColumn<?, ?> TableFirst;
    @FXML
    private TableColumn<?, ?> TableSecond;
    @FXML
    private TableColumn<?, ?> TableThird;
    @FXML
    private TableColumn<?, ?> TableFourth;
    @FXML
    private TableColumn<?, ?> TableFifth;
    @FXML
    private TableColumn<?, ?> TableSixth;
    
    String imagePath;
    @FXML
    private Tab AdminPanel;
    @FXML
    private AnchorPane adminContent;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setUsername(FXML_LoginController.getInstance().username());
        
        System.out.println(FXML_LoginController.getInstance().AdminPrivileges());
        if(FXML_LoginController.getInstance().AdminPrivileges() == false){
            AdminPanel.setDisable(true);
            adminContent.setVisible(false);
        }
        else{
            AdminPanel.setDisable(false);
        }
        imagePath = null;
    }    
    
    public FXML_mainController(){
        connection = Mysql_Connect.connectdb();
    }
    public void setUsername(String user){
        this.User_name.setText(user);
    }
   
    Account client = new Account();
    TrayNotify notification = new TrayNotify();
    Connection connection = null;
    Statement statement  = null;
    ResultSet resultSet = null;
    
    @FXML
    private void HandleRegisterAction(ActionEvent event) {
        
        String gender = null;
        // Toggle group
        ToggleGroup toggleGroup = new ToggleGroup();
        radioMale.setToggleGroup(toggleGroup);
        radioFemale.setToggleGroup(toggleGroup);
        
            if(radioMale.isSelected()){
                gender = "Male";
            }   
            else if(radioFemale.isSelected()){
                gender = "Female";
            }
            else{
                notification.PopupAnimation("Registration", "An error occured ", "ERROR", 3);
            }
        
        String firstName = txtFirstName.getText().toString();
        String lastName = txtSecondName.getText().toString();
        String surname = txtSurnameName.getText().toString();
        String id = txtIdNumber.getText().toString();
        String birthDate = txtDateOfBirth.getValue().toString();
        
        if (firstName != null && lastName !=null && surname !=null && id !=null){
            if( imagePath != null){
                client.register(firstName, lastName, surname, id,0, gender,birthDate,imagePath);
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Profile picture");
                alert.setHeaderText("The Account Picture is either empty or\n an error occuredPlease try again");
                alert.show();
            }
        }
           System.out.println(imagePath);
    }

    @FXML
    private void HandleCheckBalanceAction(ActionEvent event) {
        
        client.checkBalance(searchID.getText().toString());
        searchName.setText(client.first_name);
        searchAccNo.setText(client.Account_No);
        searchBal.setText( Integer.toString(client.balance) );
    }

    @FXML
    private void HandleDepositCashAction(ActionEvent event) {
       
        client.deposit( depositAccNo.getText().toString(), Integer.parseInt(depositAmount.getText().toString()) );
    }

    @FXML
    private void HandleDepositMpesaAction(ActionEvent event) {
    }

    @FXML
    private void HandleWithdrawAction(ActionEvent event) {
    }

    @FXML
    private void HandleApplyLoanAction(ActionEvent event) {
    }

    @FXML
    private void HandleEligibilityAction(ActionEvent event) {
    }

    @FXML
    private void HandlePayLoanCashAction(ActionEvent event) {
    }

    @FXML
    private void HandlePayLoanMpesaAction(ActionEvent event) {
    }

    @FXML
    private void HandleLogoutAction(ActionEvent event) throws IOException {
        Parent root;
        Stage home = new Stage();
        try{
            
            root = FXMLLoader.load(getClass().getResource("FXML_Login.fxml"));
            Scene scene = new Scene(root);
            home.setScene(scene);
            MainWindow.getScene().getWindow().hide();
            home.setTitle("Nakimo Login");
            home.show();
            
        }
       catch(Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    private void ImagePicker(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Image Files","*.bmp","*.png","*.jpg","*.gif"));
        fileChooser.setTitle("Open Image");
        File file = fileChooser.showOpenDialog( new Stage() );
        if( file!=null){
            imagePath = file.toURI().toString();
            System.out.println(imagePath);
            Image image = new Image(imagePath,155,133,false,false);
    
            clientPassport.setPreserveRatio(true);
            clientPassport.setSmooth(true);
            clientPassport.setImage(image);
            //clientPassport.autosize();S
            
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Image Upload error!");
            alert.setHeaderText("Please try again");
            alert.showAndWait();
            
        }
    }
   
    public void showTables(){
        
        ArrayList<String> userList = new ArrayList<>();
        
        String sql = "SELECT * FROM account";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(!resultSet.next()){
                TableView.getColumns();
                
            }
            else{
               
                
            }
        }
        catch( Exception e){
            //
            notification.fadeAnimation("Customer List", "An error occured ", "ERROR", 2);
        }   
    }
}
