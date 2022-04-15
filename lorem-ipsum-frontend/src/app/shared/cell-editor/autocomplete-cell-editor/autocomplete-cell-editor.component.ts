import { AfterViewInit, Component, ViewChild, ViewContainerRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ICellEditorAngularComp } from 'ag-grid-angular';
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
      //this.input.element.nativeElement.focus();
      this.input.element.nativeElement.click();
      this.autocomplete.setValue(this.params.value);
    }, 0);
  }

  override isPopup(): boolean {
    return false;
  }

  optionClicked(value: string){
    console.log(value)
    this.input.element.nativeElement.value = value;
  }

  getValue() {
    if (this.options.indexOf(this.autocomplete.value) > -1) {
      return this.autocomplete.value;
    }
    return '';
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

}
