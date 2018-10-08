package com.alopeyk.nativemodule.picker;

import com.alopeyk.nativemodule.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Alireza Mahmoodi
 * Created:     10/2/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class SimpleAdapter extends PickerView.Adapter<Item> {
    List<Item> items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Item getItem(int index) {
        return items.get(index);
    }

    public void setItems(List<? extends Item> items) {
        this.items.clear();
        if(items != null){
            this.items.addAll(items);
            notifyDataSetChanged();
        }
    }
}
