package main.enums;

public enum FontNames {
    ARIAL("Arial"),
    VERDANA("Verdana"),
    TAHOMA("Tahoma"),
    TIMES_NEW_ROMAN("Times New Roman"),
    COURIER_NEW("Courier New"),
    GEORGIA("Georgia"),
    CALIBRI("Calibri"),
    COMIC_SANS_MS("Comic Sans MS"),
    TREBUCHET_MS("Trebuchet MS"),
    IMPACT("Impact");

    private final String fontName;

    FontNames(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }
}
