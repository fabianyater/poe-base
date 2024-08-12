package main;

import javax.swing.*;


public class Main {


    private static final String APP_NAME = "Vehicle Management";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ventana(APP_NAME));
    }
}