import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {

    private int clickCount = 0;
    private JLabel countLabel;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Prosty Licznik Kliknięć");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        countLabel = new JLabel("Liczba kliknięć: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton clickButton = new JButton("Kliknij mnie");

        clickButton.addActionListener(e -> {
            clickCount++;
            countLabel.setText("Liczba kliknięć: " + clickCount);
        });

        frame.add(countLabel);
        frame.add(clickButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }
}