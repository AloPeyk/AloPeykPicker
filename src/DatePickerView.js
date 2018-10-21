import React, {Component} from 'react';
import { requireNativeComponent } from 'react-native';
import PropTypes from 'prop-types';

const DatePickerViewModule = requireNativeComponent('DatePickerView', null);

export default class DatePickerView extends Component {
  constructor(props: Props) {
    super(props);
    this.onSelectedItemChanged = this.onSelectedItemChanged.bind(this);
  }

  getDate(date){
  	if(!date) return -1;
  	if(typeof date == "number") return date;
  	return date.getTime();
  }

  onSelectedItemChanged(event: Event) {
    if (!this.props.onSelectedItemChanged) {
      return;
    }
    this.props.onSelectedItemChanged(event.nativeEvent);
  }

  render() {
  	const { 
  		onSelectedItemChanged,
  		selectedDate,
  		startDate,
  		endDate,
  		...remainingProps
  	} = this.props;

    return <DatePickerViewModule {...this.props} 
    	onSelectedItemChanged={this.onSelectedItemChanged} 
    	selectedDate={this.getDate(selectedDate)}
    	startDate={this.getDate(startDate)}
    	endDate={this.getDate(endDate)} />;
  }
}


DatePickerView.propTypes = {
	onSelectedItemChanged: PropTypes.func,
    selectedDate: PropTypes.oneOfType([
    	PropTypes.instanceOf(Date),
    	PropTypes.number,
    	]),
    startDate: PropTypes.oneOfType([
    	PropTypes.instanceOf(Date),
    	PropTypes.number,
    	]),
    endDate: PropTypes.oneOfType([
    	PropTypes.instanceOf(Date),
    	PropTypes.number,
    	]),
    textSize: PropTypes.number,
    textColor: PropTypes.string,
    backgroundColor: PropTypes.string,
    selectedItemBorderColor: PropTypes.string,
    itemHeight: PropTypes.number,
}