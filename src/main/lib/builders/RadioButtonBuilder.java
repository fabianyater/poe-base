package main.lib.builders;

import javax.swing.*;

public class RadioButtonBuilder extends AbstractComponentBuilder<JRadioButton> {

    public RadioButtonBuilder(String text) {
        super(text);
    }

    @Override
    public JRadioButton build() {
        JRadioButton radioButton = new JRadioButton();

        applyCommonAttributes(radioButton);

        return radioButton;
    }
}