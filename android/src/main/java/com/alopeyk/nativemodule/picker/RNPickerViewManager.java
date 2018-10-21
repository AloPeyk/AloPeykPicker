package com.alopeyk.nativemodule.picker;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.alopeyk.nativemodule.picker.pickerView.PickerView;
import com.alopeyk.nativemodule.picker.pickerView.SimpleAdapter;
import com.alopeyk.nativemodule.picker.pickerView.Utils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:      Alireza Mahmoodi
 * Created:     10/1/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class RNPickerViewManager extends SimpleViewManager<PickerView> {
    private static final String VIEW_NAME = "PickerView";
    private static final String TAG = RNPickerViewManager.class.getSimpleName();

    @Override
    public String getName() {
        return VIEW_NAME;
    }

    @Override
    protected PickerView createViewInstance(final ThemedReactContext reactContext) {
        PickerView view = new PickerView(reactContext);
        view.setAdapter(new SimpleAdapter());

        view.setOnSelectedItemChangedListener(new PickerView.OnSelectedItemChangedListener() {
            @Override
            public void onSelectedItemChanged(PickerView pickerView, int previousPosition, int selectedItemPosition) {
                WritableMap params = Arguments.createMap();
                params.putInt("position", selectedItemPosition);
                params.putString("label", ((SimpleAdapter) pickerView.getAdapter()).getItem(selectedItemPosition).getText());
//                params.putString("key", ((SimpleAdapter) pickerView.getAdapter()).getItem(selectedItemPosition).getKey());
                sendEvent(pickerView, "onSelectedItemChanged", params);
            }
        });

        return view;
    }

    @javax.annotation.Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onSelectedItemChanged", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelectedItemChanged")))
                .build();
    }

    @ReactProp(name = "backgroundColor", defaultInt = Color.WHITE, customType = "Color")
    public void setBackgroundColor(PickerView view, int backgroundColor) {
        int gradient[] = new int[]{
                (backgroundColor & 0x00ffffff) + 0xcf000000,
                (backgroundColor & 0x00ffffff) + 0x9f000000,
                (backgroundColor & 0x00ffffff) + 0x5f000000
        };
        view.setGradientColors(gradient);
        view.setBackgroundColor(backgroundColor);
    }

    @ReactProp(name = "textColor", defaultInt = Color.BLACK, customType = "Color")
    public void setTextColor(PickerView view, int textColor) {
        view.setTextColor(textColor);
    }

    @ReactProp(name = "textSize", defaultInt = 18)
    public void setTextSize(PickerView view, int textSize) {
        view.setTextSize(Utils.dp(view.getContext(), textSize));
    }

    @ReactProp(name = "items")
    public void setItems(PickerView view, ReadableArray items) {
        List<Item> output = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {

            switch (items.getType(i)) {
                case Null:
                    break;
                case String:
                    output.add(new Item(i, items.getString(i)));
                    break;
            }
        }
        ((SimpleAdapter) view.getAdapter()).setItems(output);
    }

//    @ReactProp(name = "items")
//    public void setItems(PickerView view, ReadableMap items) {
//        List<Item> output = new ArrayList<>();
//        ReadableMapKeySetIterator keys = items.keySetIterator();
//        while (keys.hasNextKey()) {
//            String key = keys.nextKey();
//
//            switch (items.getType(key)) {
//                case Null:
//                    break;
//                case String:
//                    output.add(new Item(key, items.getString(key)));
//                    break;
//            }
//        }
//        ((SimpleAdapter) view.getAdapter()).setItems(output);
//    }


    @ReactProp(name = "selectedItem", defaultInt = 0)
    public void setSelectedItem(final PickerView pickerView, final int index){
        pickerView.post(new Runnable() {
            @Override
            public void run() {
                pickerView.setSelectedItemPosition(index);
            }
        });
    }

    @ReactProp(name = "cyclic", defaultBoolean = false)
    public void setCyclic(PickerView pickerView, boolean enable){
        pickerView.setCyclic(enable);
    }

    @ReactProp(name = "itemHeight", defaultInt = -1)
    public void setItemHeight(PickerView pickerView, int height){
        if(height < 0) return;
        pickerView.setItemHeight(Utils.dp(pickerView.getContext(), height));
    }

    private void sendEvent(View view, String eventName, @Nullable WritableMap params) {
        ((ReactContext)view.getContext())
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(view.getId(), eventName, params);
    }


}
