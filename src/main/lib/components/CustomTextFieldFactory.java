package main.lib.components;

import main.lib.builders.TextFieldBuilder;

public class CustomTextFieldFactory {
    public TextFieldBuilder createDefaultPanel() {
        return new TextFieldBuilder("");
    }
}
