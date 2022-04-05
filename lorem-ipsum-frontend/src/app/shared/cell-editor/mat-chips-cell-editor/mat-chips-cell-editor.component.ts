import { AfterViewInit, Component, ViewChild, ViewContainerRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { ICellEditorAngularComp } from 'ag-grid-angular';
import { ICellEditorParams } from 'ag-grid-community';
import { COMMA, SEMICOLON, TAB } from '@angular/cdk/keycodes';

import * as _ from 'lodash';
import { Chip } from '../../cell-renderer/mat-chips-cell-renderer/mat-chips-cell-renderer.component';

@Component({
  selector: 'app-mat-chips-cell-editor',
  templateUrl: './mat-chips-cell-editor.component.html',
  styleUrls: ['./mat-chips-cell-editor.component.scss']
})
export class MatChipsCellEditorComponent implements ICellEditorAngularComp, AfterViewInit {
  params!: ICellEditorParams;

  @ViewChild("input", { read: ViewContainerRef })
  public input!: ViewContainerRef;
  chips!: Set<Chip>;
  addOnBlur = true;
  chipListPadding = 5;
  readonly separatorKeysCodes = [COMMA, SEMICOLON, TAB] as const;

  getValue() {
    let value = new Array<string>();
    this.chips.forEach(chip => value.push(`${chip.value}`))
    return value;
  }
  agInit(params: ICellEditorParams): void {
    this.params = params;
    const values = params.value as string[];
    this.chips = new Set(values.filter(v => !_.isEmpty(v)).map(v => new Chip(v)));
    this.params.api.sizeColumnsToFit();
  }

  ngAfterViewInit(): void {
    window.setTimeout(() => {
      this.input.element.nativeElement.focus();
    }, 0);
  }

  isPopup(): boolean {
    return true;
  }

  formControl = new FormControl();

  addChipFromInput(event: MatChipInputEvent) {
    if (event.value) {
      this.chips.add(new Chip(event.value));
      event.chipInput!.clear();
    }
  }
  onKeyDown(event: any): void {
    const keyCode = event.keyCode;
    this.params.api.sizeColumnsToFit();
    if (this.separatorKeysCodes.indexOf(keyCode) > -1) {
      this.preventDefaultAndPropagation(event);
    }
  }

  removeChip(chip: Chip) {
    this.chips.delete(chip);
  }

  private preventDefaultAndPropagation(event: any) {
    event.preventDefault();
    event.stopPropagation();
  }

  get width(): number {
    return this.params.column.getActualWidth();
  }

  get chipListWidth(): number {
    return this.width - 2 * this.chipListPadding;
  }

  righClick(event: any, pickerId: string) {
    event.preventDefault();
    document.getElementById(pickerId)?.click();
  }


}
