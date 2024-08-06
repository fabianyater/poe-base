package main.lib.utils;

import javax.swing.*;
import java.awt.*;

public class Utils {

    private Utils() {
    }

    public static void centerComponent(JComponent component, Dimension size) {
        Dimension labelSize = component.getPreferredSize();

        int x = (size.width - labelSize.width) / 2;
        int y = (size.height - labelSize.height) / 2;

        component.setBounds(x, y, labelSize.width, labelSize.height);
    }

    public static void centerComponentHorizontally(JComponent component, Dimension size) {
        Dimension dimension = component.getPreferredSize();

        int x = ((size.width - dimension.width) / 2) - 7;
        int y = component.getY();

        component.setBounds(x, y, dimension.width, dimension.height);
    }
}
