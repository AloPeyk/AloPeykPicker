
package com.alopeyk.nativemodule.picker;
/**
 * Author:      Alireza Mahmoudi
 * Created:     10/23/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class PickerUpdate {
    private final float width;
    private final float height;
    private final int backgroundColor;
    private final String fontFamily;
    private final int fontStyle;
    private final int color;
    private final int fontSize;
    private final int itemHeight;
    private final int selectedItemBorderColor;
    private final boolean cyclic;


    public PickerUpdate(
            float width,
            float height,
            String fontFamily,
            int backgroundColor,
            int fontStyle,
            int color,
            int fontSize,
            int itemHeight,
            int selectedItemBorderColor,
            boolean cyclic){

        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.fontFamily = fontFamily;
        this.fontStyle = fontStyle;
        this.color = color;
        this.fontSize = fontSize;
        this.itemHeight = itemHeight;
        this.selectedItemBorderColor = selectedItemBorderColor;
        this.cyclic = cyclic;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontStyle(){
        return fontStyle;
    }

    public int getColor() {
        return color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public int getSelectedItemBorderColor() {
        return selectedItemBorderColor;
    }

    public boolean isCyclic() {
        return cyclic;
    }
}


