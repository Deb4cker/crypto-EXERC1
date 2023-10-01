package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends GenericView{

    private JButton signInButton;

    public LoginView() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateSignInButton();
    }

    private void generateSignInButton(){
        signInButton = new JButton("Sign In");
        signInButton.setBounds(350, 400, 100, 25);
        add(signInButton);
        signInButton.setVisible(true);
    }

    public void addSignInButtonAction(ActionListener action){
        signInButton.addActionListener(action);
    }

    public void addLoginButtonAction(ActionListener action){
        loginButton.addActionListener(action);
    }

    public void authorization(boolean authorized){
        var message = authorized? "Autorizado!" : "Não Autorizado!";
        var messageType = authorized? JOptionPane.OK_CANCEL_OPTION : JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, "Sign In", messageType);
    }
}
