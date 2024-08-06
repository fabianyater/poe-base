package main.lib.components;

import main.lib.builders.ButtonBuilder;

public class CustomButtonFactory {
    public ButtonBuilder createDefaultButton(String text) {
        return new ButtonBuilder(text);
    }
}
