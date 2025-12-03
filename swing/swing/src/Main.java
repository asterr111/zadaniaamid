import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new PPO();
            new LayoutManagers();
            new Jpanel();
            new Jcomponent();
            new Z_przyciski();
            new Z_klawiatura();
            new Z_mysz();
            new Napisy();
            new Obrazki();
        });
    }
}