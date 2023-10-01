package Controller;

import Database.Db;
import Security.AESCBC;
import Security.SHA256;
import View.LoginView;
public class LoginController {

    private LoginView loginView;
    private Db usersFile;
    public LoginController(){
        loginView = new LoginView();
        usersFile = new Db();

        loginView.addLoginButtonAction(e -> {login();});
        loginView.addSignInButtonAction(e -> {new SignInController().openView();});
    }

    public void openView(){
        loginView.open();
    }

    private void login() {
        try {
            var inputUsername = loginView.getLabelTexts()[0] == null ? "" : loginView.getLabelTexts()[0];
            var inputPassword = loginView.getLabelTexts()[1] == null ? "" : loginView.getLabelTexts()[1];

            var user = usersFile.getByUserName(inputUsername);
            if(user.getName().equals(SHA256.encrypt(inputUsername))){

                var saltIv = usersFile.getSaltIvByPassword(inputPassword);
                var encryptedPass = AESCBC.encrypt(inputPassword, saltIv[0], saltIv[1].getBytes());

                loginView.authorization(encryptedPass.equals(user.getPassword()));
            }
            else loginView.authorization(false);

        }
        catch(Exception e){
            loginView.authorization(false);
        }
    }

}
