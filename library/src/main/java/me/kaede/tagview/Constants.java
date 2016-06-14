package me.kaede.tagview;

import android.graphics.Color;

class Constants {
    public static boolean DEBUG = true;

    // the dimens unit is dp or sp, not px
    public static final float DEFAULT_LINE_MARGIN = 5;
    public static final float DEFAULT_TAG_MARGIN = 5;
    public static final float DEFAULT_TAG_TEXT_PADDING_LEFT = 8;
    public static final float DEFAULT_TAG_TEXT_PADDING_TOP = 5;
    public static final float DEFAULT_TAG_TEXT_PADDING_RIGHT = 8;
    public static final float DEFAULT_TAG_TEXT_PADDING_BOTTOM = 5;
    public static final float LAYOUT_WIDTH_OFFSET = 2;

    public static final float DEFAULT_TAG_TEXT_SIZE = 14f;
    public static final float DEFAULT_TAG_DELETE_INDICATOR_SIZE = 14f;
    public static final float DEFAULT_TAG_LAYOUT_BORDER_SIZE = 0f;
    public static final float DEFAULT_TAG_RADIUS = 100;
    public static final int DEFAULT_TAG_LAYOUT_COLOR = Color.parseColor("#00BFFF");
    public static final int DEFAULT_TAG_LAYOUT_COLOR_PRESS = Color.parseColor("#88363636");
    public static final int DEFAULT_TAG_TEXT_COLOR = Color.parseColor("#ffffff");
    public static final int DEFAULT_TAG_DELETE_INDICATOR_COLOR = Color.parseColor("#ffffff");
    public static final int DEFAULT_TAG_LAYOUT_BORDER_COLOR = Color.parseColor("#ffffff");
    public static final String DEFAULT_TAG_DELETE_ICON = "×";
    public static final boolean DEFAULT_TAG_IS_DELETABLE = false;
}
