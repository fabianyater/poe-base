package main;

import javax.swing.*;
import java.awt.*;

class CardRenderer extends JPanel implements ListCellRenderer<Card> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Card> list, Card card, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel cardPanel = card.getCardPanel();

        if (isSelected) {
            cardPanel.setBackground(new Color(240, 240, 240));
        } else {
            cardPanel.setBackground(new Color(250, 250, 250));
        }

        return cardPanel;
    }
}

