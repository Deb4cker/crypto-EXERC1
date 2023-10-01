package Controller;

import Database.Db;
import Domain.User;
import View.SignInView;

public class SignInController {

    private Db userFile;
    private SignInView signInView;
    public SignInController(){
        signInView = new SignInView();
        userFile = new Db();

        signInView.addCreateAccountButtonAction(e -> {createAccount();});
    }

    public void openView(){
        signInView.open();
    }

    public void createAccount(){
        try {
        var username = signInView.getLabelTexts()[0];
        var password = signInView.getLabelTexts()[1];
        var confirmation = signInView.getConfirmation();
            if(password.equals(confirmation)) {
                userFile.save(new User(username, password));
                signInView.authorization(true);
            }
            else signInView.authorization(false);
        }catch (Exception e){
            signInView.authorization(false);
        }
    }


}
