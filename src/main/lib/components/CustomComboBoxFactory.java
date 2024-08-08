package main.lib.components;

import main.lib.builders.ComboBoxBuilder;

public class CustomComboBoxFactory {
    public ComboBoxBuilder createDefaultComboBox() {
        return new ComboBoxBuilder("");
    }
}
