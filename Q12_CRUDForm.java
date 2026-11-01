
/*12. Reuse the above form to perform basic database operations (CRUD) using JDBC. */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Q12_CRUDForm extends JFrame implements ActionListener {

    JTextField nameField;
    JPasswordField passwordField;

    JButton insertButton, updateButton, deleteButton, viewButton;

    Q12_CRUDForm() {

        setTitle("Student Login - CRUD");
        setSize(450, 300);
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        add(insertButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        insertButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    Connection connect() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studentdb",
            "root",
            "YOUR_PASSWORD"
        );
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        try {

            Connection con = connect();

            if (e.getSource() == insertButton) {

                String sql =
                    "INSERT INTO login(name, password) VALUES (?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, password);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Inserted!");

            }

            else if (e.getSource() == updateButton) {

                String sql =
                    "UPDATE login SET password=? WHERE name=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, password);
                ps.setString(2, name);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Updated!");

            }

            else if (e.getSource() == deleteButton) {

                String sql =
                    "DELETE FROM login WHERE name=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Deleted!");

            }

            else if (e.getSource() == viewButton) {

                String sql = "SELECT * FROM login";

                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(sql);

                String data = "";

                while (rs.next()) {
                    data += "Name: " + rs.getString("name")
                          + " | Password: " + rs.getString("password")
                          + "\n";
                }

                JOptionPane.showMessageDialog(this, data);
            }

            con.close();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        new Q12_CRUDForm();
    }
}