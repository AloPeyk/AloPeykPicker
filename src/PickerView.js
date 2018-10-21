import React, {Component} from 'react';
import { requireNativeComponent } from 'react-native';
import PropTypes from 'prop-types';

const PickerViewModule = requireNativeComponent('PickerView', null);

export default class PickerView extends Component {
  constructor(props: Props) {
    super(props);
    this.onSelectedItemChanged = this.onSelectedItemChanged.bind(this);
  }

  onSelectedItemChanged(event: Event) {
    if (!this.props.onSelectedItemChanged) {
      return;
    }
    this.props.onSelectedItemChanged(event.nativeEvent);
  }

  getItems(items){
  	// Anyway return object
  	return Array.isArray(items) ? Object.assign({}, items) : items;
  }

  render() {
  	const { onSelectedItemChanged, ...remainingProps } = this.props;
    return <PickerViewModule {...this.props}
	    // items={this.getItems(items)}
	    onSelectedItemChanged={this.onSelectedItemChanged}  />;
  }
}


PickerView.propTypes = {
	onSelectedItemChanged: PropTypes.func,
    selectedItem: PropTypes.number,
    textSize: PropTypes.number,
    cyclic: PropTypes.bool,
    textColor: PropTypes.string,
    backgroundColor: PropTypes.string,
    selectedItemBorderColor: PropTypes.string,
    items: PropTypes.array.isRequired,
    itemHeight: PropTypes.number,
}

