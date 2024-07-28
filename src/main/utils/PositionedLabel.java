package main.utils;

import main.enums.FontEnums;

import javax.swing.*;
import java.awt.*;

public class PositionedLabel extends JLabel {
    private FontEnums.LabelPosition position;

    public PositionedLabel(String text, FontEnums.LabelPosition position) {
        super(text);
        this.position = position;
    }

    public void setPosition(FontEnums.LabelPosition position) {
        this.position = position;
        revalidate();
        repaint();
    }

    @Override
    public void doLayout() {
        super.doLayout();

        if (getParent() != null) {
            Insets insets = getInsets();
            Dimension parentSize = getParent().getSize();
            Dimension textSize = getPreferredSize();

            int x = insets.left;
            int y = insets.top;

            switch (position) {
                case TOP_CENTER:
                    x = (parentSize.width - textSize.width) / 2;
                    break;
                case TOP_RIGHT:
                    x = parentSize.width - insets.right - textSize.width;
                    break;
                case CENTER_LEFT:
                    y = (parentSize.height - textSize.height) / 2;
                    break;
                case CENTER_RIGHT:
                    x = parentSize.width - insets.right - textSize.width;
                    y = (parentSize.height - textSize.height) / 2;
                    break;
                case BOTTOM_LEFT:
                    y = parentSize.height - insets.bottom - textSize.height;
                    break;
                case BOTTOM_CENTER:
                    x = (parentSize.width - textSize.width) / 2;
                    y = parentSize.height - insets.bottom - textSize.height;
                    break;
                case BOTTOM_RIGHT:
                    x = parentSize.width - insets.right - textSize.width;
                    y = parentSize.height - insets.bottom - textSize.height;
                    break;
                default:
                    x = (parentSize.width - textSize.width) / 2;
                    y = (parentSize.height - textSize.height) / 2;
            }

            setBounds(x, y, textSize.width, textSize.height);
        }
    }
}
