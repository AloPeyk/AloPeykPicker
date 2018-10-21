
package com.alopeyk.nativemodule.picker;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNPickerModule extends ReactContextBaseJavaModule {

  public RNPickerModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNPicker";
  }
}