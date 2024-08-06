package main.builders;

import main.enums.FontEnums;
import main.enums.FontNames;
import main.utils.FontUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static main.utils.FontUtils.getFontBySize;

public class LabelBuilder {
    private final String text;
    private FontEnums.LabelFontSize fontSize = FontEnums.LabelFontSize.MEDIUM;
    private Color color = Color.BLACK;
    private int alignment = SwingConstants.CENTER;
    private FontEnums.LabelFontStyle fontStyle = FontEnums.LabelFontStyle.NORMAL;
    private Border border = null;
    private Icon icon = null;
    private FontEnums.TextTransform textTransform = FontEnums.TextTransform.NONE;
    private FontNames fontName = FontNames.ARIAL;
    private String tooltip = null;
    private int x = 0;
    private int y = 0;

    public LabelBuilder(String text) {
        this.text = text;
    }

    public LabelBuilder setFontSize(FontEnums.LabelFontSize fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public LabelBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public LabelBuilder setAlignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    public LabelBuilder setFontStyle(FontEnums.LabelFontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public LabelBuilder setBorder(Border border) {
        this.border = border;
        return this;
    }

    public LabelBuilder setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public LabelBuilder setTextTransform(FontEnums.TextTransform textTransform) {
        this.textTransform = textTransform;
        return this;
    }

    public LabelBuilder setFontName(FontNames fontName) {
        this.fontName = fontName;
        return this;
    }

    public LabelBuilder setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public LabelBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public LabelBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public JLabel build() {
        String transformedText = FontUtils.transformText(text, textTransform);
        JLabel label = new JLabel("<html>" + transformedText.replace("\n", "<br>") + "</html>");
        label.setFont(getFontBySize(fontName.getFontName(), fontSize, fontStyle));
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);

        Dimension preferredSize = label.getPreferredSize();
        label.setBounds(x, y, preferredSize.width, preferredSize.height);

        if (border != null) {
            label.setBorder(border);
        }

        if (icon != null) {
            label.setIcon(icon);
        }

        if (tooltip != null) {
            label.setToolTipText(tooltip);
        }

        return label;
    }

}
