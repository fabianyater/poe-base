package main.lib.builders;

import javax.swing.*;

public class ComboBoxBuilder extends AbstractComponentBuilder<JComboBox> {

    public ComboBoxBuilder(String text) {
        super(text);
    }

    @Override
    public JComboBox build() {
        JComboBox comboBox = new JComboBox();
        applyCommonAttributes(comboBox);
        return comboBox;
    }
}