
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        super("Ekran logowania");


        setLayout(new BorderLayout());


        JPanel panelGlowny = new JPanel();
        panelGlowny.setLayout(new GridLayout(2, 2, 10, 10));


        JLabel labelUzytkownik = new JLabel("Użytkownik:");
        JTextField poleUzytkownik = new JTextField(15);

        JLabel labelHaslo = new JLabel("Hasło:");
        JPasswordField poleHaslo = new JPasswordField(15);


        panelGlowny.add(labelUzytkownik);
        panelGlowny.add(poleUzytkownik);
        panelGlowny.add(labelHaslo);
        panelGlowny.add(poleHaslo);

        add(panelGlowny, BorderLayout.CENTER);


        JPanel panelPrzyciski = new JPanel();
        panelPrzyciski.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton przyciskZaloguj = new JButton("Zaloguj");
        JButton przyciskAnuluj = new JButton("Anuluj");

        panelPrzyciski.add(przyciskZaloguj);
        panelPrzyciski.add(przyciskAnuluj);

        add(panelPrzyciski, BorderLayout.SOUTH);


        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Main::new);
    }
}