package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class GenericView extends JFrame {

    protected JLabel nameLabel;
    protected JLabel passwordLabel;
    protected JTextField nameField;
    protected JPasswordField passwordField;
    protected JButton loginButton;

    public GenericView(String title){
        setLayout(null);
        visualInit(title);
    }

    public void open(){
        this.setVisible(true);
    }
    public void close(){
        this.setVisible(false);
    }

    private void visualInit(String title){
        initComponents();
        setTitle(title);
        setResizable(false);
        setSize(500, 500);
    }

    private void initComponents(){
        generateLabels();
        generateTextFields();
        generateButtons();
    }

    protected void generateLabels(){
        nameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");

        nameLabel.setBounds(50, 75, 75, 75);
        nameLabel.setVisible(true);

        passwordLabel.setBounds(50, 125, 75, 75);
        passwordLabel.setVisible(true);

        add(nameLabel);
        add(passwordLabel);
    }

    protected void generateTextFields(){
        nameField     = new JTextField();
        passwordField = new JPasswordField();

        nameField    .setBounds(126, 100, 200, 25);
        passwordField.setBounds(126, 150, 200, 25);

        add(nameField);
        add(passwordField);

        nameField    .setVisible(true);
        passwordField.setVisible(true);
    }

    protected void generateButtons(){
        loginButton  = new JButton("Login");
        loginButton .setBounds(125, 250, 200, 25);
        add(loginButton);
        loginButton .setVisible(true);
    }

    public String[] getLabelTexts(){
        return new String[]{nameField.getText(), passwordField.getText()};
    }

    public void authorization(boolean authorized){
        var message = authorized? "Autorizado" : "Não autorizado!";
        var messageType = authorized? JOptionPane.OK_CANCEL_OPTION : JOptionPane.ERROR_MESSAGE;

        JOptionPane.showMessageDialog(null, message, "Login", messageType);
    }

    public void addLoginButtonAction(ActionListener action){
        loginButton.addActionListener(action);
    }
}
