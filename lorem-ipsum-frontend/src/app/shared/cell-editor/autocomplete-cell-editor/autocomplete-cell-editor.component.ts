import { AfterViewInit, Component, ViewChild, ViewContainerRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ICellEditorAngularComp } from 'ag-grid-angular';
import { ICellEditorParams } from 'ag-grid-community';
import * as _ from 'lodash';
import { map, Observable, startWith } from 'rxjs';
import { BaseCellEditor } from '../base-cell-editor';

@Component({
  selector: 'app-autocomplete-cell-editor',
  templateUrl: './autocomplete-cell-editor.component.html',
  styleUrls: ['./autocomplete-cell-editor.component.scss']
})
export class AutocompleteCellEditorComponent extends BaseCellEditor implements ICellEditorAngularComp, AfterViewInit {
  autocomplete = new FormControl();
  options: string[] = ["First Name", "Last Name"];
  filteredOptions!: Observable<string[]>;
  @ViewChild("input", { read: ViewContainerRef })
  public input!: ViewContainerRef;


  override agInit(params: any): void {
    super.agInit(params);
    this.options = params.options;
    this.filteredOptions = this.autocomplete.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value)),
    );
  }

  ngAfterViewInit(): void {
    window.setTimeout(() => {
      this.input.element.nativeElement.focus();
      this.autocomplete.setValue(this.params.value);
    }, 0);
  }

  override isPopup(): boolean {
    return false;
  }

  getValue() {
    var value = '';
    _.forEach(this.options, option => {
      if (option.toLowerCase() === this.autocomplete.value.toLowerCase()) {
        value = option;
        return;
      }
    })
    return value;
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

}
