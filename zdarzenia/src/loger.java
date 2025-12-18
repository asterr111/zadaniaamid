import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class loger {

    private JTextArea logArea;
    private Map<Character, Integer> keyCounts = new HashMap<>();

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Aplikacja Diagnostyczna Zdarzeń");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        JButton clearButton = new JButton("Wyczyść logi");

        // ActionListener używający klasy anonimowej
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
            }
        });

        // MouseAdapter
        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log("MYSZ: Kliknięcie w: [" + e.getX() + "], [" + e.getY() + "]");
            }
        });

        // KeyListener
        logArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                keyCounts.put(keyChar, keyCounts.getOrDefault(keyChar, 0) + 1);
                log("KLAWISZ: Wpisano: [" + keyChar + "] (łączna liczba: " + keyCounts.get(keyChar) + ")");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Nie używamy tej metody w tym zadaniu
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Nie używamy tej metody w tym zadaniu
            }
        });

        // WindowListener używający wyrażenia lambda lub WindowAdapter
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Czy na pewno chcesz zamknąć aplikację?",
                        "Potwierdzenie zamknięcia",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(clearButton, BorderLayout.SOUTH);
        frame.setVisible(true);
        logArea.requestFocusInWindow(); // Upewnienie się, że JTextArea odbiera zdarzenia klawiatury
    }

    private void log(String message) {
        logArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new loger().createAndShowGUI();
            }
        });
    }
}