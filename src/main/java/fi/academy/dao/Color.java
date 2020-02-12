package fi.academy.dao;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Color {
    private int id;
    private String name;
    private String hex;
    private int[] rgb;
    private boolean custom;

    public Color() {
    }

    public Color(String name, String hex) {
        this(name, hex, true);
    }

    public Color(String name, String hex, boolean custom) {
        this(name, hex, hex2rgb(hex), custom);
    }

    public Color(String name, String hex, int[] rgb) {
        this(name, hex, rgb, true);
    }

    public Color(String name, String hex, int[] rgb, boolean custom) {
        this.name = name;
        validateHex(hex);
        if (!hexAndRgbEqual(hex, rgb)) throw new ColorException("Hex and rgb values must match");
        this.hex = hex;
        this.rgb = rgb;
        this.custom = custom;
    }

    public static int[] hex2rgb(String hex) {
        validateHex(hex);
        int[]a = new int[3];
        a[0] = Integer.parseInt(hex.substring(1, 3), 16);
        a[1] = Integer.parseInt(hex.substring(3, 5), 16);
        a[2] = Integer.parseInt(hex.substring(5), 16);
        return a;
    }

    public static boolean hexAndRgbEqual(String hex, int [] rgb) {
        return Integer.parseInt(hex.substring(1, 3), 16) == rgb[0] &&
                Integer.parseInt(hex.substring(3, 5), 16) == rgb[1] &&
                Integer.parseInt(hex.substring(5), 16) == rgb[2];
    }

    private static void validateHex(String hex) {
        if (!Pattern.matches("#[0-9a-fA-F]{6}", hex))
            throw new ColorException(String.format("Not a valid hex \"%s\"", hex));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Color{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", hex='").append(hex).append('\'');
        sb.append(", rgb=").append(Arrays.toString(rgb));
        sb.append(", custom=").append(custom);
        sb.append('}');
        return sb.toString();
    }
}