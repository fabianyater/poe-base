package main;

import main.components.CustomButtonFactory;
import main.components.CustomLabelFactory;

import javax.swing.*;

import static main.utils.Utils.centerComponent;

public class Ventana extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    JLabel label;

    public Ventana(String title) {
        setTitle(title);
        setUpFrame();

        label = new CustomLabelFactory()
                .createDefaultLabel("Hola mundo")
                .build();

        centerComponent(label, getSize());

        JButton button = new CustomButtonFactory()
                .createDefaultButton(null)
                .setBorderPainted(false)
                .setFocusPainted(false)
                .setContentAreaFilled(false)
                .setOpaque(false)
                .setBorder(null)
                .build();

        add(label);
        add(button);

    }

    public void setUpFrame() {
        setLayout(null); // No layout manager
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Cerrar la ventana al hacer clic en cerrar
        setVisible(true);
    }
}
