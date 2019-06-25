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
        randomSelectedItem: 0,
        picker1: '-',
        picker2: '-',
        picker3: '-',
      }

      setInterval(() => { 
        var item = this.state.randomSelectedItem + 1;
        if(item > 5) item = 0;
        this.setState({randomSelectedItem: item});
      }, 1000);
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
              style={[styles.pickerView, styles.light]} 
              color="#f33"
              items={getData('foo', 10)}
              itemHeight={30}
              selectedItem={this.state.randomSelectedItem}
              onSelectedItemChanged={ (e) => {this.setState({picker1: e.label});
            }
               }/>

              <PickerView 
              style={styles.pickerView} 
              color="#333"
              cyclic={false}
              items={getData('bar', 20)}
              itemHeight={30}
              selectedItem={6}
              onSelectedItemChanged={ (e) => {this.setState({picker2: e.label})} }/>
            </View>
        </View>

        <View style={[styles.section, styles.dark]}>
            <Text style={styles.label}>{this.state.picker3}</Text>
            <View style={styles.row}>
              <DatePickerView
                style={[[styles.pickerView, styles.dark, styles.light]]}
                selectedItemBorderColor="#777"
                color="#ccc"
                itemHeight={30}
                startDate={new Date('2016','05','15')}
                endDate={new Date('2019','10','10')}
                selectedDate={new Date('2018','9','8')}
                onSelectedItemChanged={ (e) => {this.setState({picker3: e.timeString})} } />
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
    height: 150,
    marginTop: 20,
    backgroundColor: '#f2f2f2',
    fontSize: 18,
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
  light: {
    fontFamily: 'Roboto-Light', 
  }
});
