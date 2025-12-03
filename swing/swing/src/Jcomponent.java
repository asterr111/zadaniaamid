public class Jcomponent extends javax.swing.JFrame {
    public Jcomponent() {
        super("Rysowanie");

        // This now refers to the MyPanel class created above
        Jpanel panel = new Jpanel();

        add(panel);

        pack(); // Adjusts window size to fit the MyPanel preferred size
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}