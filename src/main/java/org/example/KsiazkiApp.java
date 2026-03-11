import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class KsiazkiApp extends JFrame {

    private JTable tabela;
    private JTextField tytulField, autorField, rokField;

    private static final String URL = "jdbc:mysql://localhost:3306/ksiegarnia";
    private static final String USER = "root";
    private static final String PASSWORD = "haslo";

    public KsiazkiApp() {

        setTitle("Księgozbiór");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabela = new JTable();
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(4,2));

        panel.add(new JLabel("Tytuł"));
        tytulField = new JTextField();
        panel.add(tytulField);

        panel.add(new JLabel("Autor"));
        autorField = new JTextField();
        panel.add(autorField);

        panel.add(new JLabel("Rok"));
        rokField = new JTextField();
        panel.add(rokField);

        JButton dodaj = new JButton("Dodaj");
        JButton usun = new JButton("Usuń");

        panel.add(dodaj);
        panel.add(usun);

        add(panel, BorderLayout.SOUTH);

        dodaj.addActionListener(e -> dodajKsiazke());
        usun.addActionListener(e -> usunKsiazke());

        odswiezTabele();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void dodajKsiazke() {

        String sql = "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tytulField.getText());
            ps.setString(2, autorField.getText());
            ps.setInt(3, Integer.parseInt(rokField.getText()));

            ps.executeUpdate();

            odswiezTabele();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void usunKsiazke() {

        int row = tabela.getSelectedRow();
        if(row == -1) return;

        int id = (int) tabela.getValueAt(row,0);

        String sql = "DELETE FROM ksiazki WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,id);
            ps.executeUpdate();

            odswiezTabele();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void odswiezTabele() {

        String sql = "SELECT * FROM ksiazki";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID","Tytuł","Autor","Rok"},0);

            while(rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });

            }

            tabela.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new KsiazkiApp().setVisible(true);
        });

    }
}