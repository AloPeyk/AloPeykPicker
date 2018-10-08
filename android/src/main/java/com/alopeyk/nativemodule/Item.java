package com.alopeyk.nativemodule;

import com.alopeyk.nativemodule.picker.PickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Alireza Mahmoodi
 * Created:     9/26/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class Item implements PickerView.PickerItem {
    private String title;
    private String key;

    public Item(String key, String title) {
        this.key = key;
        this.title = title;
    }

    public Item(int key, String title){
        this(String.valueOf(key), title);
    }

    @Override
    public String getText() {
        return title;
    }

    public String getKey() {
        return key;
    }

}
