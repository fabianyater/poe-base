package main.utils;

import main.enums.FontEnums;

import java.awt.*;

public class FontUtils {
    private FontUtils() {
    }

    public static String transformText(String text, FontEnums.TextTransform textTransform) {
        return switch (textTransform) {
            case UPPERCASE -> text.toUpperCase();
            case LOWERCASE -> text.toLowerCase();
            case CAPITALIZE -> capitalizeWords(text);
            case NONE -> text;
        };
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s");
        StringBuilder capitalizedText = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedText.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return capitalizedText.toString().trim();
    }

    public static Font getFontBySize(String fontName, FontEnums.LabelFontSize fontSize, FontEnums.LabelFontStyle fontStyle) {
        int style = switch (fontStyle) {
            case NORMAL -> Font.PLAIN;
            case BOLD -> Font.BOLD;
            case ITALIC -> Font.ITALIC;
            case BOLD_ITALIC -> Font.BOLD | Font.ITALIC;
        };

        int size = switch (fontSize) {
            case SMALL -> 12;
            case MEDIUM -> 16;
            case LARGE -> 20;
            case EXTRA_LARGE -> 24;
        };

        return new Font(fontName, style, size);
    }
}
