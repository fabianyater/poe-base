package main.lib.builders;

import javax.swing.*;
import java.awt.*;

public class ButtonBuilder extends AbstractComponentBuilder<JButton> {
    private boolean isBorderPainted = true;
    private boolean contentAreaFilled = true;
    private boolean focusPainted = false;
    private Cursor cursor;

    public ButtonBuilder(String text) {
        super(text);
    }

    public ButtonBuilder setBorderPainted(boolean isBorderPainted) {
        this.isBorderPainted = isBorderPainted;
        return this;
    }

    public ButtonBuilder setContentAreaFilled(boolean contentAreaFilled) {
        this.contentAreaFilled = contentAreaFilled;
        return this;
    }

    public ButtonBuilder setFocusPainted(boolean focusPainted) {
        this.focusPainted = focusPainted;
        return this;
    }

    public ButtonBuilder setCursor(Cursor cursor) {
        this.cursor = cursor;
        return this;
    }

    @Override
    public JButton build() {
        JButton button = new JButton();

        button.setBorderPainted(isBorderPainted);
        button.setContentAreaFilled(contentAreaFilled);
        button.setFocusPainted(focusPainted);
        button.setCursor(cursor);

        applyCommonAttributes(button);

        return button;
    }
}
