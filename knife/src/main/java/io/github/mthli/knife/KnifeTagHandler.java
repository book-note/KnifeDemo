/*
 * Copyright (C) 2015 Matthew Lee
 * Copyright (C) 2013-2015 Dominik Schürmann <dominik@dominikschuermann.de>
 * Copyright (C) 2013-2015 Juha Kuitunen
 * Copyright (C) 2013 Mohammed Lakkadshaw
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.mthli.knife;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.StrikethroughSpan;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.mthli.knife.utils.ThemeUtil;

public class KnifeTagHandler implements Html.TagHandler {
    private static final String BULLET_LI = "li";
    private static final String STRIKETHROUGH_S = "s";
    private static final String STRIKETHROUGH_STRIKE = "strike";
    private static final String STRIKETHROUGH_DEL = "del";
    private static final String MARK = "mark";
    private int defaultMarkBackgroundColor = Color.parseColor("#F9E79F");
    private int markBackgroundColor = defaultMarkBackgroundColor;
    private Context context;

    private static final HashMap<Integer, Integer> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put(Color.parseColor("#FFE1F9"), Color.parseColor("#665A64"));
        COLOR_MAP.put(Color.parseColor("#FDFBCA"), Color.parseColor("#8D8B42"));
        COLOR_MAP.put(Color.parseColor("#C8F2EE"), Color.parseColor("#7C9299"));
        COLOR_MAP.put(Color.parseColor("#C8EDF8"), Color.parseColor("#506062"));
        COLOR_MAP.put(Color.parseColor("#B1C7E7"), Color.parseColor("#323333"));
        COLOR_MAP.put(Color.parseColor("#A6CED1"), Color.parseColor("#6E8788"));
        COLOR_MAP.put(Color.parseColor("#D4E8A4"), Color.parseColor("#818F66"));
        COLOR_MAP.put(Color.parseColor("#F0D472"), Color.parseColor("#A89C00"));
        COLOR_MAP.put(Color.parseColor("#F2A4B8"), Color.parseColor("#AD7683"));
        COLOR_MAP.put(Color.parseColor("#EB88E1"), Color.parseColor("#C070B7"));
        COLOR_MAP.put(Color.parseColor("#ECD8FE"), Color.parseColor("#83798D"));
        COLOR_MAP.put(Color.parseColor("#DABDB9"), Color.parseColor("#A9928F"));
        COLOR_MAP.put(Color.parseColor("#DFDFDF"), Color.parseColor("#626262"));
    }

    public KnifeTagHandler(Context context) {
        this.context = context;
    }

    private static class Li {
    }

    private static class Strike {
    }

    private static class Mark {
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (opening) {
            if (tag.equalsIgnoreCase(BULLET_LI)) {
                if (output.length() > 0 && output.charAt(output.length() - 1) != '\n') {
                    output.append("\n");
                }
                start(output, new Li());
            } else if (tag.equalsIgnoreCase(STRIKETHROUGH_S) || tag.equalsIgnoreCase(STRIKETHROUGH_STRIKE) || tag.equalsIgnoreCase(STRIKETHROUGH_DEL)) {
                start(output, new Strike());
            } else if (tag.equalsIgnoreCase(MARK)) {
                markBackgroundColor = getMarkBackgroundColor(xmlReader);
                start(output, new Mark());
            }
        } else {
            if (tag.equalsIgnoreCase(BULLET_LI)) {
                if (output.length() > 0 && output.charAt(output.length() - 1) != '\n') {
                    output.append("\n");
                }
                end(output, Li.class, new BulletSpan());
            } else if (tag.equalsIgnoreCase(STRIKETHROUGH_S) || tag.equalsIgnoreCase(STRIKETHROUGH_STRIKE) || tag.equalsIgnoreCase(STRIKETHROUGH_DEL)) {
                end(output, Strike.class, new StrikethroughSpan());
            } else if (tag.equalsIgnoreCase(MARK)) {
                end(output, Mark.class, new BackgroundColorSpan(optimizeDarkMode(markBackgroundColor)));
            }
        }
    }

    private void start(Editable output, Object mark) {
        output.setSpan(mark, output.length(), output.length(), Spanned.SPAN_MARK_MARK);
    }

    private void end(Editable output, Class kind, Object... replaces) {
        Object last = getLast(output, kind);
        int start = output.getSpanStart(last);
        int end = output.length();
        output.removeSpan(last);

        if (start != end) {
            for (Object replace : replaces) {
                output.setSpan(replace, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private static Object getLast(Editable text, Class kind) {
        Object[] spans = text.getSpans(0, text.length(), kind);

        if (spans.length == 0) {
            return null;
        } else {
            for (int i = spans.length; i > 0; i--) {
                if (text.getSpanFlags(spans[i - 1]) == Spannable.SPAN_MARK_MARK) {
                    return spans[i - 1];
                }
            }

            return null;
        }
    }

    private int getMarkBackgroundColor(XMLReader xmlReader) {
        String styleAttr = getProperty(xmlReader, "style");
        if (styleAttr == null) {
            return defaultMarkBackgroundColor;
        }
        Pattern pattern = Pattern.compile("background-color:(.*)");
        Matcher matcher = pattern.matcher(styleAttr);
        if (matcher.find()) {
            try {
                if (matcher.groupCount() >= 1) {
                    return Integer.parseInt(matcher.group(1));
                } else {
                    return defaultMarkBackgroundColor;
                }
            } catch (NumberFormatException e) {
                return defaultMarkBackgroundColor;
            }
        } else {
            return defaultMarkBackgroundColor;
        }
    }

    /**
     * 利用反射获取html标签的属性值
     *
     * @param xmlReader
     * @param property
     * @return
     */
    private String getProperty(XMLReader xmlReader, String property) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);

            for (int i = 0; i < len; i++) {
                // 这边的property换成你自己的属性名就可以了
                if (property.equals(data[i * 5 + 1])) {
                    return data[i * 5 + 4];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int optimizeDarkMode(int markBackgroundColor) {
        boolean isDarkMode = ThemeUtil.isNightMode(context);
        if (!isDarkMode) {
            return markBackgroundColor;
        }
        Integer darkModeColor = COLOR_MAP.get(markBackgroundColor);
        if (darkModeColor == null) {
            return defaultMarkBackgroundColor;
        } else {
            return darkModeColor;
        }
    }
}
