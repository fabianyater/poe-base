package main.utils;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static void centerLabel(JLabel label, Dimension size) {
        Dimension parentSize = size;
        Dimension labelSize = label.getPreferredSize();

        int x = (parentSize.width - labelSize.width) / 2;
        int y = (parentSize.height - labelSize.height) / 2;

        label.setBounds(x, y, labelSize.width, labelSize.height);
    }
}
