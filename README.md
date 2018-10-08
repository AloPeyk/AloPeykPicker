# alopeyk-picker

## Getting started

`$ npm install alopeyk-picker --save`

### Mostly automatic installation

`$ react-native link alopeyk-picker`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.alopeyk.nativemodule.RNPickerPackage;` to the imports at the top of the file
  - Add `new RNPickerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':alopeyk-picker'
  	project(':alopeyk-picker').projectDir = new File(rootProject.projectDir, 	'../node_modules/alopeyk-picker/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':alopeyk-picker')
  	```


## Usage
```javascript
import {PickerView, DatePickerView} from 'alopeyk-picker';

// TODO: What to do with the module?
<PickerView .../>
<DatePickerView .../>
```
  

## PickerView Properties


|Props                	|Type                   |is Required  |
|-----------------------|-----------------------|-------------|
|`onSelectedItemChanged`|`'func'`           	|-            |
|`selectedItem`			|`'number'`				|-            |
|`textSize`				|`'number'`            	|-            |
|`cyclic`				|`'bool'`            	|-            |
|`textColor`			|`'string'`            	|-            |
|`backgroundColor`		|`'string'`            	|-            |
|`items`				|`'array'`            	|True         |
|`itemHeight`			|`'number'`            	|-            |


## DatePickerView Properties


|Props                	|Type                   |is Required  |
|-----------------------|-----------------------|-------------|
|`onSelectedItemChanged`|`'func'`           	|-            |
|`selectedDate`			|`'Date'` `'number'`	|-            |
|`startDate`			|`'Date'` `'number'`	|-            |
|`endDate`				|`'Date'` `'number'`	|-            |
|`textSize`				|`'number'`            	|-            |
|`textColor`			|`'string'`            	|-            |
|`backgroundColor`		|`'string'`            	|-            |
|`itemHeight`			|`'number'`            	|-            |

