
/*11. Create a form containing fields name and password respectively, using Applet as a container. */

import javax.swing.*;
import java.awt.*;

public class Q11_SecureLoginForm extends JFrame {

    JTextField nameField;
    JPasswordField passwordField;

    Q11_SecureLoginForm() {

        setTitle("Login Form");
        setSize(400, 250);
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Q11_SecureLoginForm();
    }
}