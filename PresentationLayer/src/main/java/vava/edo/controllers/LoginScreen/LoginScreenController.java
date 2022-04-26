package vava.edo.controllers.LoginScreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import vava.edo.Exepctions.LoginScreen.EmptyLoginFields;
import vava.edo.Exepctions.LoginScreen.IncorrectCredentials;
import vava.edo.Handlers.UserHandler;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    private User user = null;
    private User deserializedUser = null;
    private boolean rememberMeState = false;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Label wrongCredentials;

    @FXML
    private CheckBox checkBoxRememberMe;
    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLoginGuest;

    @FXML
    private Button btnForgotPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        deserializedUser = UserHandler.loadSerializedUser();

        if(deserializedUser != null){

            rememberMeState = deserializedUser.isRememberMe();

            textUsername.setText(deserializedUser.getUsername());
            textPassword.setText(deserializedUser.getPassword());
            checkBoxRememberMe.setSelected(deserializedUser.isRememberMe());
        }

    }

    @FXML
    protected void handleLoginButton() throws IOException {

        try {

            if(deserializedUser == null){
                this.user = UserHandler.loginUser(textUsername.getText(), textPassword.getText(), wrongCredentials);

            }else {
                if (textUsername.getText().equals(deserializedUser.getUsername()) &&
                        textPassword.getText().equals(deserializedUser.getPassword())) {
                    this.user = UserHandler.loginUser(deserializedUser.getUsername(), deserializedUser.getPassword(), wrongCredentials);
                } else {
                    this.user = UserHandler.loginUser(textUsername.getText(), textPassword.getText(), wrongCredentials);
                }
            }


            // Null shouldn't be possible TODO
            // Serialization takes place here if desired
            this.user.setRememberMe(this.rememberMeState);

            if(this.user.isRememberMe()) {
                UserHandler.serializeUser(this.user);
            }else {
                new PrintWriter(UserHandler.getPATH()).close();
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/Menu.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));
            AnchorPane todoScreen = loader.load();
            MenuScreenController menuScreen = loader.getController();
            menuScreen.initialize(user, rootPane);

            rootPane.getChildren().setAll(todoScreen);
        }
        catch (EmptyLoginFields | IncorrectCredentials e){
            e.printStackTrace();
        }

    }

    /**
     * Method that on click of the remember me box changes the state
     */
    @FXML
    public void rememberMe() {
        this.rememberMeState = !this.rememberMeState;
    }



    /**
     *
     */
    public void handleRegisterButton() {
        try {
            AnchorPane registerScreen = FXMLLoader.load(getClass().getResource("/vava/edo/Register.fxml"),
                    ResourceBundle.getBundle("Localization Bundle"));

            rootPane.getChildren().setAll(registerScreen);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}