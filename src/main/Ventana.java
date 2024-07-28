package main;

import main.components.CustomLabelFactory;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public enum WindowMode {
        LAYOUT,
        BOUNDS
    }

    public Ventana(String title, int width, int height, WindowMode mode) {
        setTitle(title);

        if (mode == WindowMode.LAYOUT) {
            setSize(width, height);
            setLayout(new BorderLayout());
            JLabel label = new JLabel("Bienvenido a la Aplicación POE", SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
        } else if (mode == WindowMode.BOUNDS) {
            new Bounds().configure(this, width, height);

            JLabel customTitle = new CustomLabelFactory().createDefaultLabel("Hola mundo").setColor(Color.white).build();

            add(customTitle);
        }


        setVisible(true);
    }
}
