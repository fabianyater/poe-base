package main.lib.components;

import main.lib.enums.FontEnums;
import main.lib.builders.LabelBuilder;

import javax.swing.*;
import java.awt.*;

public class CustomLabelFactory {

    public LabelBuilder createDefaultLabel(String text) {
        return new LabelBuilder(text);
    }

    public JLabel createWarningLabel(String text) {
        return new LabelBuilder(text)
                .setColor(Color.RED)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .build();
    }

    public JLabel createWarningLabel2(String text) {
        return new LabelBuilder(text)
                .setColor(Color.RED)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .build();
    }

    public JLabel createSuccessLabel(String text) {
        return new LabelBuilder(text)
                .setColor(Color.GREEN)
                .setFontStyle(FontEnums.LabelFontStyle.BOLD)
                .build();
    }

    public LabelBuilder createLabelWithIcon(Icon icon) {
        return (LabelBuilder) new LabelBuilder()
                .setIcon(icon)
                .setSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    public LabelBuilder createLabelWithTextAndIcon(String text, Icon icon) {
        return (LabelBuilder) new LabelBuilder(text)
                .setIcon(icon)
                .setAlignment(SwingConstants.LEFT)
                .setIconTextGap(3)
                .setSize(new Dimension(80, icon.getIconHeight()));
    }
}
