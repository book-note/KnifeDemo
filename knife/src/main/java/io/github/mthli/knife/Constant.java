package io.github.mthli.knife;

import android.graphics.Color;

import java.util.HashMap;

public class Constant {
    public static final HashMap<Integer, Integer> LIGHT_TO_DARK_COLOR_MAP = new HashMap<>();
    public static final HashMap<Integer, Integer> DARK_TO_LIGHT_COLOR_MAP = new HashMap<>();
    public static int DEFAULT_MARK_BACKGROUND_COLOR = Color.parseColor("#F9E79F");

    static {
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#FFE1F9"), Color.parseColor("#665A64"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#FDFBCA"), Color.parseColor("#8D8B42"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#C8F2EE"), Color.parseColor("#7C9299"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#C8EDF8"), Color.parseColor("#506062"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#B1C7E7"), Color.parseColor("#323333"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#A6CED1"), Color.parseColor("#6E8788"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#D4E8A4"), Color.parseColor("#818F66"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#F0D472"), Color.parseColor("#A89C00"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#F2A4B8"), Color.parseColor("#AD7683"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#EB88E1"), Color.parseColor("#C070B7"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#ECD8FE"), Color.parseColor("#83798D"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#DABDB9"), Color.parseColor("#A9928F"));
        LIGHT_TO_DARK_COLOR_MAP.put(Color.parseColor("#DFDFDF"), Color.parseColor("#626262"));

        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#665A64"), Color.parseColor("#FFE1F9"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#8D8B42"), Color.parseColor("#FDFBCA"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#7C9299"), Color.parseColor("#C8F2EE"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#506062"), Color.parseColor("#C8EDF8"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#323333"), Color.parseColor("#B1C7E7"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#6E8788"), Color.parseColor("#A6CED1"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#818F66"), Color.parseColor("#D4E8A4"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#A89C00"), Color.parseColor("#F0D472"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#AD7683"), Color.parseColor("#F2A4B8"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#C070B7"), Color.parseColor("#EB88E1"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#83798D"), Color.parseColor("#ECD8FE"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#A9928F"), Color.parseColor("#DABDB9"));
        DARK_TO_LIGHT_COLOR_MAP.put(Color.parseColor("#626262"), Color.parseColor("#DFDFDF"));
    }

}
