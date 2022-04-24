import { I } from '@angular/cdk/keycodes';
import { AfterViewInit, Component, ViewChild, ViewContainerRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSelect } from '@angular/material/select';
import { ICellEditorAngularComp } from 'ag-grid-angular';
import * as _ from 'lodash';
import { OptionDto } from '../../models/optionDto.model';
import { BaseCellEditor } from '../base-cell-editor';

@Component({
  selector: 'app-multi-select-cell-editor',
  templateUrl: './multi-select-cell-editor.component.html',
  styleUrls: ['./multi-select-cell-editor.component.scss']
})
export class MultiSelectCellEditorComponent extends BaseCellEditor implements AfterViewInit, ICellEditorAngularComp {
  selectControl = new FormControl();
  options: OptionDto[] = [];
  isFreeText: boolean = false;
  optionDto: OptionDto = new OptionDto();
  @ViewChild("input", { read: ViewContainerRef })
  public input!: ViewContainerRef;

  @ViewChild(MatSelect) matSelect!: MatSelect;


  override agInit(params: any): void {
    super.agInit(params);
    this.options = _.find(params.options, group => group.name === params.node.data.dataType).options;
    this.isFreeText = _.isNil(this.options) || _.isEmpty(this.options);
    if (!this.isFreeText) {
      this.selectControl.setValue(params.value)
    } else {
      if (!_.isNil(params.value) && !_.isEmpty(params.value)) {
        const option = params.value[0] as OptionDto;
        if (!_.isNil(option)) {
          this.optionDto.optionData = option.optionData;
        }
      }
    }
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      if (this.isFreeText) {
        this.input.element.nativeElement.click();
      } else {
        this.matSelect.open();
      }
    }, 0);
  }

  getValue() {
    if (this.isFreeText) {
      if (!_.isNil(this.optionDto.optionData) && !_.isEmpty(this.optionDto.optionData)) {
        return [this.optionDto];
      }
      return [];
    }
    return this.selectControl.value;
  }

  getVal(optionEnum: string, value: string) {
    return { optionEnum: optionEnum, optionData: value } as OptionDto;
  }

  compare(object1: OptionDto, object2: OptionDto) {
    return object1 && object2 && object1.optionEnum === object2.optionEnum && object1.optionData === object2.optionData;
  }

}
