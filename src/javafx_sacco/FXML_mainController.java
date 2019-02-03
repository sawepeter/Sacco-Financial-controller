package javafx_sacco;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import backend.Notify;
/**
 * FXML Controller class
 *
 * @author Pato
 */
public class FXML_mainController implements Initializable {

    @FXML
    private AnchorPane MainWindow;
    @FXML
    private TextField first_name;
    @FXML
    private TextField second_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleRegisterAction(ActionEvent event) {
        Notify x = new Notify();

        x.PopupAnimation("Registration", "Successfuly registered a new customer", "SUCCESS", 3);
    }

    @FXML
    private void HandleCheckBalanceAction(ActionEvent event) {
    }

    @FXML
    private void HandleDepositCashAction(ActionEvent event) {
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
    private void HandleLogoutAction(ActionEvent event) {
    }
    
}
