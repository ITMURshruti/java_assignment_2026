
 /*create a form  containing fields name and passwoed respectively, using applet as a container.*/

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code="Q7_LoginFormApplet" width="350" height="180"></applet> */
public class Q07_LoginFormApplet extends Applet implements ActionListener {
    private final TextField tfName = new TextField(20);
    private final TextField tfPass = new TextField(20);
    private final Button submit = new Button("Submit");
    private final Label status = new Label("");

    public void init() {
        setLayout(new GridLayout(4, 2, 5, 5));
        tfPass.setEchoChar('*');
        add(new Label("Name:"));     add(tfName);
        add(new Label("Password:")); add(tfPass);
        add(new Label(""));          add(submit);
        add(new Label("Status:"));   add(status);
        submit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (tfName.getText().trim().isEmpty() || tfPass.getText().isEmpty())
            status.setText("Both fields required");
        else
            status.setText("Welcome, " + tfName.getText().trim());
    }

    public static void main(String[] args) {
        Frame f = new Frame("Form (Applet in Frame)");
        Q07_LoginFormApplet a = new Q07_LoginFormApplet();
        f.add(a); f.setSize(350, 180);
        a.init(); f.setVisible(true); a.start();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { f.dispose(); System.exit(0); }
        });
    }
}