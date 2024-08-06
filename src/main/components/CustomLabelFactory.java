package main.components;

import main.enums.FontEnums;
import main.builders.LabelBuilder;

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
}
