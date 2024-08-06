package main.utils;

import javax.swing.*;
import java.awt.*;

public class Utils {

    private Utils() {
    }

    public static void centerLabel(JLabel label, Dimension size) {
        Dimension labelSize = label.getPreferredSize();

        int x = (size.width - labelSize.width) / 2;
        int y = (size.height - labelSize.height) / 2;

        label.setBounds(x, y, labelSize.width, labelSize.height);
    }
}
