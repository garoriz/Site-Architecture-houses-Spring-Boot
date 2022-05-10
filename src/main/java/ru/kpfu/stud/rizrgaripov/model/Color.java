package ru.kpfu.stud.rizrgaripov.model;

public class Color {
    private String hex;
    private String rgb;
    private String hsl;
    private String backgroundColor;

    public Color(String hex, String rgb, String hsl, String backgroundColor) {
        this.hex = hex;
        this.rgb = rgb;
        this.hsl = hsl;
        this.backgroundColor = backgroundColor;
    }

    public String getHex() {
        return hex;
    }

    public String getRgb() {
        return rgb;
    }

    public String getHsl() {
        return hsl;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public void setHsl(String hsl) {
        this.hsl = hsl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
