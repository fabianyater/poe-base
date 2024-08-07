package main.lib.builders;

import javax.swing.*;
import java.awt.*;

public class PanelBuilder extends AbstractComponentBuilder<JPanel> {
    private LayoutManager layoutManager = null;

    public PanelBuilder(String text) {
        super(text);
    }

    public PanelBuilder setLayout(LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    @Override
    public JPanel build() {
        JPanel panel = new JPanel();

        panel.setLayout(layoutManager);

        applyCommonAttributes(panel);

        return panel;
    }
}
