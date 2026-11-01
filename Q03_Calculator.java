
/*
Write a program to design a calculator using the AWT controls provided in Java. */

import java.awt.*;
import java.awt.event.*;

public class Q03_Calculator extends Frame implements ActionListener {
    private final TextField display = new TextField("0");
    private double acc = 0;
    private String op = "";
    private boolean startNew = true;

    Q03_Calculator() {
        super("AWT Calculator");
        display.setEditable(false);
        display.setFont(new Font("SansSerif", Font.BOLD, 26));
        display.setBackground(Color.WHITE);
        add(display, BorderLayout.NORTH);

        Panel p = new Panel(new GridLayout(5, 4, 5, 5));
        String[] keys = {"C","<","%","/",
                         "7","8","9","*",
                         "4","5","6","-",
                         "1","2","3","+",
                         "+/-","0",".","="};
        for (String k : keys) {
            Button b = new Button(k);
            b.setFont(new Font("SansSerif", Font.BOLD, 18));
            b.addActionListener(this);
            p.add(b);
        }
        add(p, BorderLayout.CENTER);
        setSize(320, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { dispose(); System.exit(0); }
        });
        setVisible(true);
    }

    private double val() {
        try { return Double.parseDouble(display.getText()); }
        catch (NumberFormatException e) { return 0; }
    }
    private void show(double d) {
        if (d == (long) d && Math.abs(d) < 1e15) display.setText(String.valueOf((long) d));
        else display.setText(String.valueOf(d));
    }
    private void error(String m) { display.setText(m); acc = 0; op = ""; startNew = true; }

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        String t = display.getText();
        if (c.matches("[0-9]")) {
            if (startNew || t.equals("0")) display.setText(c); else display.setText(t + c);
            startNew = false;
        } else if (c.equals(".")) {
            if (startNew) { display.setText("0."); startNew = false; }
            else if (!t.contains(".")) display.setText(t + ".");
        } else if (c.equals("C")) {
            acc = 0; op = ""; startNew = true; display.setText("0");
        } else if (c.equals("<")) {
            if (startNew) return;
            if (t.length() <= 1 || (t.length() == 2 && t.startsWith("-"))) { display.setText("0"); startNew = true; }
            else display.setText(t.substring(0, t.length() - 1));
        } else if (c.equals("+/-")) {
            if (!t.equals("0") && !t.startsWith("Error"))
                display.setText(t.startsWith("-") ? t.substring(1) : "-" + t);
        } else if (c.equals("%")) {
            show(val() / 100); startNew = true;
        } else if (c.equals("=")) {
            if (!op.isEmpty()) {
                double r = calc(acc, op, val());
                if (Double.isNaN(r)) { error("Error: divide by 0"); return; }
                show(r); op = ""; startNew = true;
            }
        } else {                                   // + - * /
            if (op.isEmpty()) acc = val();
            else if (!startNew) {                  // chaining: 2 + 3 * ...
                double r = calc(acc, op, val());
                if (Double.isNaN(r)) { error("Error: divide by 0"); return; }
                acc = r; show(acc);
            }                                      // operator do baar dabaya to bas replace
            op = c; startNew = true;
        }
    }

    private double calc(double a, String o, double b) {
        switch (o) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b == 0 ? Double.NaN : a / b;
        }
        return b;
    }

    public static void main(String[] args) { new Q03_Calculator(); }
}