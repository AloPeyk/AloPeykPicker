package com.alopeyk.nativemodule.picker;

import android.graphics.Color;
import android.graphics.Typeface;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Author:      Alireza Mahmoudi
 * Created:     10/22/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class PickerShadowNode extends LayoutShadowNode {
    public static final int UNSET = -1;
    private String mFontFamily = "";
    private int mBackgroundColor = Color.WHITE;
    private int mFontStyle = Typeface.NORMAL;
    private int mColor = Color.BLACK;
    private int mFontSize = 18;
    private int mItemHeight = UNSET;
    private int mSelectedItemBorderColor = 0x7f777777;
    private boolean mCyclic = false;
    private float mHeight = 150;

    public PickerShadowNode(){

    }

    public PickerShadowNode(PickerShadowNode node){
        super();
        mFontFamily = node.mFontFamily;
        mBackgroundColor = node.mBackgroundColor;
        mFontStyle = node.mFontStyle;
        mColor = node.mColor;
        mFontSize = node.mFontSize;
        mItemHeight = node.mItemHeight;
        mSelectedItemBorderColor = node.mSelectedItemBorderColor;
        mCyclic = node.mCyclic;
    }

    //@Override
    protected LayoutShadowNode copy() {
        return new PickerShadowNode(this);
    }

    @Override
    public void onBeforeLayout() {
        super.onBeforeLayout();
    }

    @Override
    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);

        if (true) {
            PickerUpdate pickerUpdate = new PickerUpdate(
                    getLayoutWidth(),
                    getLayoutHeight(),
                    mFontFamily,
                    mBackgroundColor,
                    mFontStyle,
                    mColor,
                    mFontSize,
                    mItemHeight,
                    mSelectedItemBorderColor,
                    mCyclic );
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), pickerUpdate);
        }
    }

    @ReactProp(name = ViewProps.BACKGROUND_COLOR, defaultInt = Color.WHITE, customType = "Color")
    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_FAMILY)
    public void setFontFamily(String fontFamily) {
        mFontFamily = fontFamily;
        markUpdated();
    }

    @ReactProp(name = ViewProps.COLOR, defaultInt = Color.BLACK, customType = "Color")
    public void setColor(int color) {
       mColor = color;
       markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_SIZE, defaultInt = 18)
    public void setFontSize(int fontSize) {
        mFontSize = fontSize;
        markUpdated();
    }
//    @ReactProp(name = ViewProps.HEIGHT, defaultInt = 18)
//    public void setHeight(int height) {
//        mHeight = height;
//        markUpdated();
//    }

//    @ReactProp(name = ViewProps.FONT_STYLE, defaultInt = Typeface.NORMAL)
//    public void setFontStyle(int fontStyle) {
//        mFontStyle = fontStyle;
//        markUpdated();
//    }

    @ReactProp(name = "cyclic", defaultBoolean = false)
    public void setCyclic(boolean enable){
        mCyclic = enable;
        markUpdated();
    }

    @ReactProp(name = "itemHeight", defaultInt = UNSET)
    public void setItemHeight(int itemHeight){
        if(itemHeight < 0) return;
        mItemHeight = itemHeight;
        markUpdated();
    }

    @ReactProp(name = "selectedItemBorderColor", defaultInt = 0x7f777777, customType = "Color")
    public void setSelectedItemBorderColor(int selectedItemBorderColor) {
        mSelectedItemBorderColor = selectedItemBorderColor;
        markUpdated();
    }
}
