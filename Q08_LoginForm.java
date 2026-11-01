/*8.The input entered in these fields should be stored in the database. Use JDBC connectivity for implementing this program */

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q08_LoginForm extends Frame implements ActionListener {
    Label nameLabel, passwordLabel, msgLabel;
    TextField nameField, passwordField;
    Button submitButton;

    // Database Credentials (Apne MySQL ke hisaab se change kar lena)
    static final String DB_URL = "jdbc:mysql://localhost:3306/college_db";
    static final String DB_USER = "root";
    static final String DB_PASS = "your_mysql_password"; // Apne MySQL ka password yahan daalein

    public Q08_LoginForm() {
        setTitle("Login Form with JDBC");
        setSize(350, 250);
        setLayout(new FlowLayout());

        nameLabel = new Label("Username: ");
        nameField = new TextField(15);

        passwordLabel = new Label("Password: ");
        passwordField = new TextField(15);
        passwordField.setEchoChar('*'); // Password ko hide karne ke liye

        submitButton = new Button("Register / Save");
        msgLabel = new Label("");

        // Components ko Frame par add karna
        add(nameLabel);
        add(nameField);
        add(passwordLabel);
        add(passwordField);
        add(submitButton);
        add(msgLabel);

        // Button click event register karna
        submitButton.addActionListener(this);

        // Window close event handler
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = nameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            msgLabel.setText("Please fill in all fields!");
            msgLabel.setForeground(Color.RED);
            return;
        }

        // JDBC Database Insertion Logic
        try {
            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Open Connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // 3. Create SQL Insert Statement
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 4. Execute Query
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                msgLabel.setText("Data stored successfully!");
                msgLabel.setForeground(Color.BLUE);
                nameField.setText("");
                passwordField.setText("");
            }

            // 5. Close Connection
            pstmt.close();
            conn.close();

        } catch (ClassNotFoundException ex) {
            msgLabel.setText("MySQL JDBC Driver not found!");
            msgLabel.setForeground(Color.RED);
            ex.printStackTrace();
        } catch (SQLException ex) {
            msgLabel.setText("Database Error: " + ex.getMessage());
            msgLabel.setForeground(Color.RED);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Q08_LoginForm form = new Q08_LoginForm();
        form.setVisible(true);
    }
}