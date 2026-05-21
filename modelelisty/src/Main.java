import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JLabel countLabel;

    public Main() {
        setTitle("Menedżer Zadań");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10)); // Dodajemy odstępy (gap)


        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);


        JPanel northPanel = new JPanel(new BorderLayout());
        taskInput = new JTextField();
        JButton addButton = new JButton("Dodaj");
        northPanel.add(taskInput, BorderLayout.CENTER);
        northPanel.add(addButton, BorderLayout.EAST);


        add(new JScrollPane(taskList), BorderLayout.CENTER);


        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        JButton deleteButton = new JButton("Usuń zaznaczone");
        countLabel = new JLabel("Liczba zadań: 0", SwingConstants.CENTER);
        southPanel.add(deleteButton);
        southPanel.add(countLabel);


        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);


        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> removeTask(taskList.getSelectedIndex()));


        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = taskList.locationToIndex(evt.getPoint());
                    removeTask(index);
                }
            }
        });

        setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Zadanie nie może być puste!");
            return;
        }
        listModel.addElement(task);
        taskInput.setText("");
        updateCounter();
    }

    private void removeTask(int index) {
        if (index != -1) {
            listModel.remove(index);
            updateCounter();
        }
    }

    private void updateCounter() {
        countLabel.setText("Liczba zadań: " + listModel.getSize());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}