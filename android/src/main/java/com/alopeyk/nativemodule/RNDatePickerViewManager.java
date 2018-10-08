package com.alopeyk.nativemodule;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.alopeyk.nativemodule.picker.DateTimePickerView;
import com.alopeyk.nativemodule.picker.Utils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Calendar;
import java.util.Map;

/**
 * Author:      Alireza Mahmoodi
 * Created:     10/2/18
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public class RNDatePickerViewManager extends SimpleViewManager<DateTimePickerView> {
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

    @ReactProp(name = "backgroundColor", defaultInt = Color.WHITE, customType = "Color")
    public void setBackgroundColor(DateTimePickerView view, int backgroundColor) {
        int gradient[] = new int[]{
                (backgroundColor & 0x00ffffff) + 0xcf000000,
                (backgroundColor & 0x00ffffff) + 0x9f000000,
                (backgroundColor & 0x00ffffff) + 0x5f000000
        };
        view.setGradientColors(gradient);
        view.setBackgroundColor(backgroundColor);
    }

    @ReactProp(name = "textColor", defaultInt = Color.BLACK, customType = "Color")
    public void setTextColor(DateTimePickerView view, int textColor) {
        view.setTextColor(textColor);
    }

    @ReactProp(name = "textSize", defaultInt = 18)
    public void setTextSize(DateTimePickerView view, int textSize) {
        view.setTextSize(Utils.dp(view.getContext(), textSize));
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

    @ReactProp(name = "itemHeight", defaultInt = -1)
    public void setItemHeight(DateTimePickerView view, int height){
        if(height < 0) return;
        view.setItemHeight(height);
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
}
