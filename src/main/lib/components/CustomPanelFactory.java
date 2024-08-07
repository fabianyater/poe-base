package main.lib.components;

import main.lib.builders.PanelBuilder;

public class CustomPanelFactory {
    public PanelBuilder createDefaultPanel() {
        return new PanelBuilder("");
    }
}
