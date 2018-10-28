package com.alopeyk.nativemodule.picker;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.alopeyk.nativemodule.picker.pickerView.PickerView;
import com.alopeyk.nativemodule.picker.pickerView.SimpleAdapter;
import com.alopeyk.nativemodule.picker.pickerView.Utils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactFontManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:      Alireza Mahmoodi
 * Created:     10/1/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class RNPickerViewManager extends BaseViewManager<PickerView, PickerShadowNode> {
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

    @ReactProp(name = "items")
    public void setItems(PickerView view, ReadableArray items) {
        List<Item> output = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {

            switch (items.getType(i)) {
                case String:
                    output.add(new Item(i, items.getString(i)));
                    break;
                case Number:
                    String label;
                    double valD = items.getDouble(i);
                    if(valD % 1 > 0.d){
                        label = String.valueOf(valD);
                    }else{
                        label = String.valueOf(items.getInt(i));
                    }
                    output.add(new Item(i, label));
                    break;
                case Boolean:
                    output.add(new Item(i, String.valueOf(items.getBoolean(i))));
                    break;
                case Map:
                    output.add(new Item(i, "[Object]"));
                    break;
                case Array:
                    output.add(new Item(i, "[Array]"));
                    break;
                case Null:
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

    private void sendEvent(View view, String eventName, @Nullable WritableMap params) {
        ((ReactContext)view.getContext())
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(view.getId(), eventName, params);
    }

    @Override
    public PickerShadowNode createShadowNodeInstance() {
        return new PickerShadowNode();
    }


    @Override
    public Class<? extends PickerShadowNode> getShadowNodeClass() {
        return PickerShadowNode.class;
    }

    @Override
    public void updateExtraData(PickerView root, Object extraData) {
        PickerUpdate update = (PickerUpdate) extraData;
        root.setLayoutParams(new ViewGroup.LayoutParams((int)update.getWidth(), (int)update.getHeight()));
        root.setBackgroundColor(update.getBackgroundColor());
        root.setCyclic(update.isCyclic());
        root.setItemHeight(Utils.dp(root.getContext(), update.getItemHeight()));
        root.setSelectedItemBorderColor(update.getSelectedItemBorderColor());
        root.setTextSize(Utils.dp(root.getContext(), update.getFontSize()));
        root.setTextColor(update.getColor());

        if(!update.getFontFamily().isEmpty()){
            Typeface typeface = ReactFontManager.getInstance().getTypeface(
                    update.getFontFamily(),
                    update.getFontStyle(),
                    root.getContext().getAssets());

            root.setTypeFace(typeface);
        }
    }

    @Override
    protected void onAfterUpdateTransaction(PickerView view) {
        super.onAfterUpdateTransaction(view);
    }
}
