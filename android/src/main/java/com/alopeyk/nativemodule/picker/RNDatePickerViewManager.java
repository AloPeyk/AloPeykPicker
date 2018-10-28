package com.alopeyk.nativemodule.picker;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alopeyk.nativemodule.picker.pickerView.DateTimePickerView;
import com.alopeyk.nativemodule.picker.pickerView.Utils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactFontManager;

import java.util.Calendar;
import java.util.Map;

/**
 * Author:      Alireza Mahmoodi
 * Created:     10/2/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class RNDatePickerViewManager extends BaseViewManager<DateTimePickerView, PickerShadowNode> {
    private static final String VIEW_NAME = "DatePickerView";


    @Override
    public String getName() {
        return VIEW_NAME;
    }

    @Override
    protected DateTimePickerView createViewInstance(ThemedReactContext reactContext) {
        DateTimePickerView view = new DateTimePickerView(reactContext);
        view.setType(DateTimePickerView.TYPE_YEAR_MONTH_DAY);

        view.setOnSelectedDateChangedListener(new DateTimePickerView.OnSelectedDateChangedListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onSelectedDateChanged(DateTimePickerView view, Calendar date) {
                WritableMap params = Arguments.createMap();
                params.putDouble("timestamp", date.getTime().getTime());
                params.putInt("year", date.get(Calendar.YEAR));
                params.putInt("month", date.get(Calendar.MONTH));
                params.putInt("day", date.get(Calendar.DAY_OF_MONTH));
                params.putString("timeString", String.format("%d-%02d-%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH)));

                sendEvent(view, "onSelectedItemChanged", params);
            }
        });

        return view;
    }

    @ReactProp(name = "selectedDate", defaultDouble = -1)
    public void setSelectedData(DateTimePickerView view, double selectedDate) {
        if(selectedDate < 0) return;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) selectedDate);
        view.setSelectedDate(calendar);
    }

    @ReactProp(name = "startDate", defaultDouble = -1)
    public void setStartData(DateTimePickerView view, double startDate) {
        if(startDate < 0) return;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) startDate);
        view.setStartDate(calendar);
    }

    @ReactProp(name = "endDate", defaultDouble = -1)
    public void setEndData(DateTimePickerView view, double endDate) {
        if(endDate < 0) return;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) endDate);
        view.setEndDate(calendar);
    }

    @javax.annotation.Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onSelectedItemChanged", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelectedItemChanged")))
                .build();
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
    public void updateExtraData(DateTimePickerView root, Object extraData) {
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
}
