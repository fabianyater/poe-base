package main;

import javax.swing.*;
import java.awt.*;

public class Bounds {
    public void configure(JFrame frame, int width, int height) {
        frame.setLayout(null); // No layout manager
        frame.setSize(width, height);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLocationRelativeTo(null);
    }
}
