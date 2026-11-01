
/*9. Write a program to create a small portal which contains the registration form of students. Use JDBC. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Q09_StudentRegistration extends JFrame implements ActionListener {

    JTextField idField, nameField, emailField, courseField;
    JButton registerButton;

    Q09_StudentRegistration() {

        setTitle("Student Registration Form");
        setSize(450, 350);
        setLayout(new FlowLayout());

        add(new JLabel("Student ID:"));
        idField = new JTextField(20);
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        add(new JLabel("Course:"));
        courseField = new JTextField(20);
        add(courseField);

        registerButton = new JButton("Register");
        add(registerButton);

        registerButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String email = emailField.getText();
        String course = courseField.getText();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "serpent21"
            );

            String query =
                "INSERT INTO registration(id, name, email, course) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, course);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                this,
                "Registration Successful!"
            );

            ps.close();
            con.close();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        new Q09_StudentRegistration();
    }
}