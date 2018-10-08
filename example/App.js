/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import {PickerView, DatePickerView} from 'alopeyk-picker';


type Props = {};
export default class App extends Component<Props> {
  constructor() {
      super()
      this.state = {
         picker1: '-',
         picker2: '-',
         picker3: '-',
      }
   }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Alopeyk Picker Module</Text>
        <View style={styles.section}>
          <View style={styles.row}>
            <Text style={styles.label}>{this.state.picker1}</Text>
            <Text style={styles.label}>{this.state.picker2}</Text>
          </View>
          <View style={styles.row}>
            <PickerView 
              backgroundColor="#f2f2f2"
              textColor="#f33"
              items={getData('foo', 10)}
              itemHeight={30}
              selectedItem={2}
              textSize={18}
              onSelectedItemChanged={ (e) => {this.setState({picker1: e.label}) } }
              style={styles.pickerView} />

              <PickerView 
              backgroundColor="#f2f2f2"
              textColor="#333"
              cyclic={false}
              items={getData('bar', 20)}
              selectedItem={6}
              textSize={18} 
              onSelectedItemChanged={ (e) => {this.setState({picker2: e.label})} }
              style={styles.pickerView} />
            </View>
        </View>

        <View style={[styles.section, styles.dark]}>
            <Text style={styles.label}>{this.state.picker3}</Text>
            <View style={styles.row}>
              <DatePickerView
                backgroundColor="#333"
                textColor="#ccc"
                textSize={18}
                itemHeight={90}
                startDate={new Date('2016','05','15')}
                endDate={new Date('2019','10','10')}
                selectedDate={new Date('2018','9','8')}
                onSelectedItemChanged={ (e) => {this.setState({picker3: e.timeString})} }
                style={styles.pickerView} />
            </View>
        </View>
      </View>
    );
  }

}


function getData(name, count){
  var items = [];
  for(let i = 0; i < count; i++){
    items[i] = name + " " + (i + 1);
  }
  return items;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    alignItems: 'stretch',
    backgroundColor: '#f2f2f2',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    fontWeight: 'bold',
  },
  pickerView: {
    flex: 1,
    height: 100,
    marginTop: 20,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-around',

  },
  label: {
    flex: 1,
    marginBottom: 5,
    paddingTop: 10,
    marginTop: 25,
    textAlign: 'center',
    color: '#aaa',
  },
  dark: {
    backgroundColor: '#333',
  },
  section: {
    flex: 1,
    flexDirection: 'column',
    paddingTop: 20,
    paddingBottom: 30,
  },
});
