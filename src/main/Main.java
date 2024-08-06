package main;

import javax.swing.*;


public class Main {


    private static final String APP_NAME = "Nombre de la aplicación";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ventana(APP_NAME));
    }
}