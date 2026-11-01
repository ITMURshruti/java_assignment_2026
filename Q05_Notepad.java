
/*write a program to create some of the features for notepad. use swings for designing this application */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class Q05_Notepad extends JFrame {
    private final JTextArea ta = new JTextArea();
    private final JFileChooser fc = new JFileChooser();
    private final UndoManager undo = new UndoManager();
    private File file = null;
    private boolean dirty = false;

    public Q05_Notepad() {
        ta.setFont(new Font("Monospaced", Font.PLAIN, 16));
        add(new JScrollPane(ta));
        ta.getDocument().addUndoableEditListener(e -> undo.addEdit(e.getEdit()));
        ta.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { setDirty(true); }
            public void removeUpdate(DocumentEvent e) { setDirty(true); }
            public void changedUpdate(DocumentEvent e) { }
        });
        setJMenuBar(buildMenu());
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { exit(); }
        });
        updateTitle();
    }

    private JMenuBar buildMenu() {
        JMenuBar mb = new JMenuBar();
        int k = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        JMenu f = new JMenu("File");
        f.add(item("New", KeyEvent.VK_N, k, e -> newDoc()));
        f.add(item("Open...", KeyEvent.VK_O, k, e -> open()));
        f.add(item("Save", KeyEvent.VK_S, k, e -> save()));
        f.add(item("Save As...", 0, 0, e -> saveAs()));
        f.addSeparator();
        f.add(item("Exit", 0, 0, e -> exit()));

        JMenu ed = new JMenu("Edit");
        ed.add(item("Undo", KeyEvent.VK_Z, k, e -> { if (undo.canUndo()) undo.undo(); }));
        ed.add(item("Redo", KeyEvent.VK_Y, k, e -> { if (undo.canRedo()) undo.redo(); }));
        ed.addSeparator();
        JMenuItem cut = new JMenuItem(new javax.swing.text.DefaultEditorKit.CutAction());
        cut.setText("Cut");   cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, k));
        JMenuItem copy = new JMenuItem(new javax.swing.text.DefaultEditorKit.CopyAction());
        copy.setText("Copy"); copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, k));
        JMenuItem paste = new JMenuItem(new javax.swing.text.DefaultEditorKit.PasteAction());
        paste.setText("Paste"); paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, k));
        ed.add(cut); ed.add(copy); ed.add(paste);
        ed.addSeparator();
        ed.add(item("Select All", KeyEvent.VK_A, k, e -> ta.selectAll()));
        ed.add(item("Time/Date", KeyEvent.VK_F5, 0, e -> ta.replaceSelection(new java.util.Date().toString())));

        JMenu fm = new JMenu("Format");
        JCheckBoxMenuItem wrap = new JCheckBoxMenuItem("Word Wrap");
        wrap.addActionListener(e -> { ta.setLineWrap(wrap.isSelected()); ta.setWrapStyleWord(true); });
        fm.add(wrap);
        fm.add(item("Font Size...", 0, 0, e -> {
            String s = JOptionPane.showInputDialog(this, "Font size (8-72):", ta.getFont().getSize());
            try {
                int n = Integer.parseInt(s.trim());
                if (n >= 8 && n <= 72) ta.setFont(ta.getFont().deriveFont((float) n));
            } catch (Exception ignored) { }
        }));

        mb.add(f); mb.add(ed); mb.add(fm);
        return mb;
    }

    private JMenuItem item(String text, int key, int mask, ActionListener al) {
        JMenuItem m = new JMenuItem(text);
        if (key != 0) m.setAccelerator(KeyStroke.getKeyStroke(key, mask));
        m.addActionListener(al);
        return m;
    }

    private void setDirty(boolean d) { dirty = d; updateTitle(); }
    private void updateTitle() {
        setTitle((dirty ? "*" : "") + (file == null ? "Untitled" : file.getName()) + " - Notepad");
    }

    private boolean confirmSave() {            // false = user ne Cancel dabaya
        if (!dirty) return true;
        int r = JOptionPane.showConfirmDialog(this, "Save changes?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
        if (r == JOptionPane.CANCEL_OPTION || r == JOptionPane.CLOSED_OPTION) return false;
        if (r == JOptionPane.YES_OPTION) return save();
        return true;
    }

    private void newDoc() {
        if (!confirmSave()) return;
        ta.setText(""); file = null; undo.discardAllEdits(); setDirty(false);
    }

    private void open() {
        if (!confirmSave()) return;
        if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File f = fc.getSelectedFile();
        try {
            ta.setText(Files.readString(f.toPath()));
            file = f; ta.setCaretPosition(0); undo.discardAllEdits(); setDirty(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot open file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean save() { return file == null ? saveAs() : write(file); }

    private boolean saveAs() {
        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return false;
        File f = fc.getSelectedFile();
        if (f.exists() && JOptionPane.showConfirmDialog(this, "Overwrite " + f.getName() + "?", "Confirm",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return false;
        return write(f);
    }

    private boolean write(File f) {
        try {
            Files.writeString(f.toPath(), ta.getText());
            file = f; setDirty(false);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot save: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void exit() { if (confirmSave()) { dispose(); System.exit(0); } }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Q05_Notepad().setVisible(true));
    }
}