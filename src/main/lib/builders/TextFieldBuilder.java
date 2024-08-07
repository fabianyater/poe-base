package main.lib.builders;

import javax.swing.*;

public class TextFieldBuilder extends AbstractComponentBuilder<JTextField> {

    public TextFieldBuilder(String text) {
        super(text);
    }

    @Override
    public JTextField build() {
        JTextField textField = new JTextField();

        applyCommonAttributes(textField);

        return textField;
    }
}