import { Component, HostListener, OnInit } from '@angular/core';
import { ColDef, GridApi, GridReadyEvent } from 'ag-grid-community';
import { AutocompleteCellEditorComponent } from '../shared/cell-editor/autocomplete-cell-editor/autocomplete-cell-editor.component';
import { MatChipsCellEditorComponent } from '../shared/cell-editor/mat-chips-cell-editor/mat-chips-cell-editor.component';
import { IconCellRendererComponent } from '../shared/cell-renderer/icon-cell-renderer/icon-cell-renderer.component';
import { MatChipsCellRendererComponent } from '../shared/cell-renderer/mat-chips-cell-renderer/mat-chips-cell-renderer.component';
import { RowModel } from './model';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {
  private gridApi!: GridApi;

  constructor() { }

  ngOnInit(): void {
  }

  defaultColDef: ColDef = {
    lockPosition: true,
    editable: true,
    resizable: true,
    sortable: true,
    autoHeight: true
  }

  columnDefs: ColDef[] = [
    { field: '', sortable: false, rowDrag: true, editable: false, cellStyle: { 'padding': '1rem 0 0 0' }, maxWidth: 42 },
    { field: '', checkboxSelection: true, sortable: false, editable: false, cellStyle: { 'padding': '1rem 0 0 0' }, maxWidth: 20 },
    { field: 'dataType', headerName: 'Datatype', cellEditor: AutocompleteCellEditorComponent },
    { field: 'name', headerName: 'Name' },
    { field: 'example', headerName: 'Example' },
    { field: 'option', headerName: 'Option', cellRenderer: MatChipsCellRendererComponent, cellEditor: MatChipsCellEditorComponent },
    { field: 'input', headerName: 'Input' },
    { field: 'regex', headerName: 'Regex' },
    { field: '', headerName: '', cellRenderer: IconCellRendererComponent, editable: false, cellStyle: { 'padding': '0' }, sortable: false, maxWidth: 42, cellRendererParams: { iconName: 'delete' } },
  ];

  rowData = [
    new RowModel('Vorname', 'name', 'John', 'Reports;Documents;', 'www.deinname.ch/name', ''),
    new RowModel('Nachname', 'nachname', 'Smith', 'International;', '-', ''),
    new RowModel('IBAN', 'iban', 'DE07 1234 1234 1234 1234 12', 'Germany;', '-', '')
  ];

  onGridReady(params: GridReadyEvent) {
    this.gridApi = params.api;
    this.gridApi.sizeColumnsToFit();
  }

  addNewRow() {
    this.gridApi.applyTransaction({ add: [RowModel.createEmptyRow()] });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.gridApi.sizeColumnsToFit();
  }
}
