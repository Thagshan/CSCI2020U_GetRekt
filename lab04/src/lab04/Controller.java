package lab04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

public class Controller {
    @FXML private Label lblEmailStatus;
    @FXML private TextField txtUsername;
    @FXML private TextField txtFullName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhoneNumber;

    public void btnRegister_OnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String fullName = txtFullName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();

        lblEmailStatus.setText(!EmailValidator.getInstance().isValid(email) ? "Invalid E-Mail Address" : "");

        System.out.println(String.format("Username: %s\nFull Name: %s\nEmail: %s\nPhone #: %s",
                username, fullName, email, phoneNumber));
    }
}
