package main.lib.components;

import main.lib.builders.RadioButtonBuilder;

public class CustomRadioButtonFactory {
    public RadioButtonBuilder createDefaultPanel(String text) {
        return new RadioButtonBuilder(text);
    }
}
