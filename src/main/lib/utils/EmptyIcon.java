package main.lib.utils;

import javax.swing.*;
import java.awt.*;

public class EmptyIcon implements Icon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // Nothing to show
    }

    @Override
    public int getIconWidth() {
        return 12;
    }

    @Override
    public int getIconHeight() {
        return 12;
    }
}
