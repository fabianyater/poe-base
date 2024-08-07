package main.lib.builders;

import javax.swing.*;

public class PanelBuilder extends AbstractComponentBuilder<JPanel> {
    public PanelBuilder(String text) {
        super(text);
    }

    @Override
    public JPanel build() {
        return new JPanel();
    }
}
