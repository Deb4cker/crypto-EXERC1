package View;

import javax.swing.*;
import java.awt.event.ActionListener;


public class SignInView extends GenericView{
    private JButton createAccountButton;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;

    public SignInView() {
        super("Sign In");
        generateConfirmationComponents();
        generateCreateAccountButton();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addCreateAccountButtonAction(ActionListener action){
        this.createAccountButton.addActionListener(action);
    }

    public String getConfirmation(){
        return confirmPasswordField.getText();
    }

    protected void generateCreateAccountButton(){
        loginButton.setVisible(false);
        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(125, 250, 200, 25);
        add(createAccountButton);
        createAccountButton.setVisible(true);
    }

    private void generateConfirmationComponents(){
        confirmPasswordField = new JPasswordField();
        confirmPasswordLabel = new JLabel("Confirm Pass.: ");

        confirmPasswordLabel.setBounds(25, 200, 200, 25);
        add(confirmPasswordLabel);
        confirmPasswordLabel.setVisible(true);

        confirmPasswordField.setBounds(126, 200, 200, 25);
        add(confirmPasswordField);
        confirmPasswordField.setVisible(true);
    }

    public void authorization(boolean authorized){
        var message = authorized? "Sucesso!" : "Algo deu errado!";
        var messageType = authorized? JOptionPane.OK_CANCEL_OPTION : JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, "Sign In", messageType);
    }
}
