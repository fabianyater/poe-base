package main.lib.components;

import main.lib.builders.ButtonBuilder;
import main.lib.builders.LabelBuilder;
import main.lib.enums.FontEnums;
import main.lib.utils.EmptyIcon;

import javax.swing.*;
import java.awt.*;

import static main.lib.utils.FontUtils.transformText;

public class CustomButtonFactory {
    public ButtonBuilder createDefaultButton(String text) {
        return new ButtonBuilder(text);
    }

    public ButtonBuilder createButtonWithTextAndIcon(String text, Icon icon, Color borderColor, Color backgroundColor) {
        return (ButtonBuilder) new ButtonBuilder(text)
                .setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .setIcon(icon)
                .setSize(new Dimension(160, 40))
                .setBackground(backgroundColor)
                .setColor(Color.WHITE)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setBorder(BorderFactory.createLineBorder(borderColor, 1))
                .setTooltip("Click here to perform an action")
                .setOpaque(true);
    }



    public String getParsedText(String text) {
        if (text != null) {
            return text.replaceAll("<[^>]*>", ""); // Eliminar etiquetas HTML
        }

        return null;
    }

    public JButton createCustomRadioButton(String text, Color borderColor, int x) {
        return new ButtonBuilder(text.toUpperCase())
                .setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                .setSize(new Dimension(80, 40))
                .setColor(Color.BLACK)
                .setFontSize(FontEnums.LabelFontSize.SMALL)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .setBorder(BorderFactory.createLineBorder(borderColor))
                .setX(x)
                .setY(30)
                .build();
    }

    public JCheckBox createCustomCheckBoxButton(String text, Color fillColor, Color borderColor, int x, int y) {
        String transformedText = transformText(text, FontEnums.TextTransform.UPPERCASE);

        JCheckBox checkBox = new JCheckBox(transformedText) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int size = getHeight() - 2;
                if (this.isSelected()) {
                    g.setColor(fillColor); // Color del relleno
                    g.fillRect(1, 1, size, size);
                } else {
                    g.setColor(borderColor); // Color del borde
                    g.drawRect(1, 1, size, size);
                }
            }
        };

        checkBox.setBounds(x, y, 125, 20);
        checkBox.setIcon(new EmptyIcon());
        checkBox.setFocusPainted(false);
        checkBox.setBackground(Color.WHITE);
        checkBox.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        checkBox.setIconTextGap(10);
        checkBox.setFont(checkBox.getFont().deriveFont(Font.PLAIN));

        return checkBox;
    }
}
