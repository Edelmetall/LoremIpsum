import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';
import * as _ from 'lodash';

@Component({
  selector: 'app-mat-chips-cell-renderer',
  templateUrl: './mat-chips-cell-renderer.component.html',
  styleUrls: ['./mat-chips-cell-renderer.component.scss']
})
export class MatChipsCellRendererComponent implements ICellRendererAngularComp {
  private params!: ICellRendererParams;
  private id!: string;
  chips!: Set<Chip>;

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

  remove(chip: Chip): void {
    this.chips.delete(chip);

    var rowNode = this.params.api.getRowNode(this.id)!;
    rowNode.setDataValue('option', this.getValue());
  }

  private getValue() {
    let value = "";
    this.chips.forEach(chip => value += `${chip.value};`)
    return value;
  }

  private setValues(params: ICellRendererParams) {
    const values = params.value as string[];
    this.chips = new Set(values.filter(v => !_.isEmpty(v)).map(v => new Chip(v)));
  }

  righClick(event: any, pickerId: string) {
    event.preventDefault();
    document.getElementById(pickerId)?.click();
  }

}

export class Chip {
  value!: string;
  color!: string;

  constructor(value: string) {
    this.value = value;
  }
}
