package main.lib.builders;

import javax.swing.*;

public class LabelBuilder extends AbstractComponentBuilder<JLabel> {

    public LabelBuilder(String text) {
        super(text);
    }

    public LabelBuilder() {
        super(null);
    }

    @Override
    public JLabel build() {
        JLabel label = new JLabel();
        applyCommonAttributes(label);
        return label;
    }
}
