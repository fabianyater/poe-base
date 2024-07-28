package main;

import javax.swing.*;


public class Main {


    private static final String APP_NAME = "Nombre de la aplicación";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        Ventana.WindowMode mode = Ventana.WindowMode.BOUNDS;
        SwingUtilities.invokeLater(() -> new Ventana(APP_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, mode));
    }
}