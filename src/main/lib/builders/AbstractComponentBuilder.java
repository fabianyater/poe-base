package main.lib.builders;

import main.lib.enums.FontEnums;
import main.lib.enums.FontNames;
import main.lib.utils.FontUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import static main.lib.utils.FontUtils.getFontBySize;

public class AbstractComponentBuilder<T extends JComponent> {
    protected final String text;
    protected FontEnums.LabelFontSize fontSize = FontEnums.LabelFontSize.MEDIUM;
    protected Color color = Color.BLACK;
    protected int alignment = SwingConstants.CENTER;
    protected FontEnums.LabelFontStyle fontStyle = FontEnums.LabelFontStyle.NORMAL;
    protected Border border = null;
    protected Icon icon = null;
    protected FontEnums.TextTransform textTransform = FontEnums.TextTransform.NONE;
    protected FontNames fontName = FontNames.ARIAL;
    protected String tooltip = null;
    protected int x = 0;
    protected int y = 0;
    protected Dimension size = null;
    private ActionListener actionListener;
    private MouseListener mouseListener;
    private Color background = Color.WHITE;

    public AbstractComponentBuilder(String text) {
        this.text = text;
    }

    public AbstractComponentBuilder<T> setFontSize(FontEnums.LabelFontSize fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public AbstractComponentBuilder<T> setColor(Color color) {
        this.color = color;
        return this;
    }

    public AbstractComponentBuilder<T> setAlignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    public AbstractComponentBuilder<T> setFontStyle(FontEnums.LabelFontStyle fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public AbstractComponentBuilder<T> setBorder(Border border) {
        this.border = border;
        return this;
    }

    public AbstractComponentBuilder<T> setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public AbstractComponentBuilder<T> setTextTransform(FontEnums.TextTransform textTransform) {
        this.textTransform = textTransform;
        return this;
    }

    public AbstractComponentBuilder<T> setFontName(FontNames fontName) {
        this.fontName = fontName;
        return this;
    }

    public AbstractComponentBuilder<T> setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public AbstractComponentBuilder<T> setX(int x) {
        this.x = x;
        return this;
    }

    public AbstractComponentBuilder<T> setY(int y) {
        this.y = y;
        return this;
    }

    public AbstractComponentBuilder<T> setSize(Dimension size) {
        this.size = size;
        return this;
    }

    public AbstractComponentBuilder<T> setActionListener(ActionListener actionListener) { // Método para establecer ActionListener
        this.actionListener = actionListener;
        return this;
    }

    public AbstractComponentBuilder<T> setMouseListener(MouseListener mouseListener) { // Método para establecer ActionListener
        this.mouseListener = mouseListener;
        return this;
    }

    public AbstractComponentBuilder<T> setBackground(Color background) {
        this.background = background;
        return this;
    }

    protected void applyCommonAttributes(T component) {
        if (text != null && !text.isEmpty()) {
            String transformedText = FontUtils.transformText(text, textTransform);
            if (component instanceof JLabel) {
                ((JLabel) component).setText("<html>" + transformedText.replace("\n", "<br>") + "</html>");
            } else if (component instanceof JButton) {
                ((JButton) component).setText("<html>" + transformedText.replace("\n", "<br>") + "</html>");
            }
        }

        component.setFont(getFontBySize(fontName.getFontName(), fontSize, fontStyle));
        component.setForeground(color);

        if (component instanceof JLabel) {
            ((JLabel) component).setHorizontalAlignment(alignment);
        }

        if (size != null) {
            component.setPreferredSize(size);
            component.setSize(size);
            component.setBounds(x, y, size.width, size.height);
        } else {
            Dimension preferredSize = component.getPreferredSize();
            component.setBounds(x, y, preferredSize.width, preferredSize.height);
        }

        if (border != null) {
            component.setBorder(border);
        }

        if (icon != null) {
            if (component instanceof JLabel) {
                ((JLabel) component).setIcon(icon);
            } else if (component instanceof JButton) {
                ((JButton) component).setIcon(icon);
            }
        }

        if (tooltip != null) {
            component.setToolTipText(tooltip);
        }

        if (background != null) {
            component.setBackground(background);
        }

        if (actionListener != null) { // Añadir ActionListener al botón
            ((JButton) component).addActionListener(actionListener);
        }

        if (mouseListener != null) { // Añadir ActionListener al botón
            component.addMouseListener(mouseListener);
        }

    }

    public T build() {
        return null;
    }
}
