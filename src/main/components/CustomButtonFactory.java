package main.components;

import main.builders.ButtonBuilder;

public class CustomButtonFactory {
    public ButtonBuilder createDefaultButton(String text) {
        return new ButtonBuilder(text);
    }
}
