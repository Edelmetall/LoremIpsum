import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';
import * as _ from 'lodash';
import { OptionDto } from '../../models/optionDto.model';

@Component({
  selector: 'app-mat-chips-cell-renderer',
  templateUrl: './mat-chips-cell-renderer.component.html',
  styleUrls: ['./mat-chips-cell-renderer.component.scss']
})
export class MatChipsCellRendererComponent implements ICellRendererAngularComp {
  private params!: ICellRendererParams;
  private id!: string;
  chips!: OptionDto[];

  constructor() { }

  refresh(params: ICellRendererParams): boolean {
    this.params = params;
    this.setValues(params);
    return true;
  }

  agInit(params: ICellRendererParams): void {
    this.id = String(params.node.id);
    this.refresh(params);
  }

  remove(chip: OptionDto): void {
    const index = this.chips.indexOf(chip);
    this.chips.splice(index, 1);

    var rowNode = this.params.api.getRowNode(this.id)!;
    rowNode.setDataValue('option', this.getValue());
  }

  private getValue() {
    return this.chips;
  }

  private setValues(params: ICellRendererParams) {
    this.chips = new Array<OptionDto>();
    _.forEach(params.value, (value: OptionDto) => {
      if (!_.isNil(value.optionData) && !_.isEmpty(value.optionData))
        this.chips.push(value);
    });
  }

  righClick(event: any, pickerId: string) {
    event.preventDefault();
    document.getElementById(pickerId)?.click();
  }

}

export class Chip {
  name!: string;
  options!: any;
  value!: string;
  color!: string;

  constructor(value: string) {
    this.value = value;
  }
}
