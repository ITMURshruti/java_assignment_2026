
/*
Write a program to animate a string on an Applet. Use the concept of Multithreading. */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/* <applet code="Q2_AnimateString" width="500" height="150"></applet> */
public class Q02_AnimateString extends Applet implements Runnable {
    private final String msg = "Hello, Java Multithreading!";
    private int x = 0;
    private volatile boolean running;
    private Thread t;

    public void init() {
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setFont(new Font("Monospaced", Font.BOLD, 28));
    }
    public void start() { running = true; t = new Thread(this); t.start(); }
    public void stop()  { running = false; }

    public void run() {
        while (running) {
            x += 5;
            int w = getFontMetrics(getFont()).stringWidth(msg);
            if (x > getWidth()) x = -w;
            repaint();
            try { Thread.sleep(50); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
        }
    }
    public void update(Graphics g) { paint(g); }   // flicker kam
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        g.drawString(msg, x, getHeight() / 2);
    }

    public static void main(String[] args) {
        Frame f = new Frame("Animated String");
        Q02_AnimateString a = new Q02_AnimateString();
        f.add(a); f.setSize(500, 150);
        a.init(); f.setVisible(true); a.start();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { a.stop(); f.dispose(); System.exit(0); }
        });
    }
}