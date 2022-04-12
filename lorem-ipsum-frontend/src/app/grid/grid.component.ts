import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {ColDef, GridApi, GridReadyEvent} from 'ag-grid-community';
import * as _ from 'lodash';
import {
  AutocompleteCellEditorComponent
} from '../shared/cell-editor/autocomplete-cell-editor/autocomplete-cell-editor.component';
import {MatChipsCellEditorComponent} from '../shared/cell-editor/mat-chips-cell-editor/mat-chips-cell-editor.component';
import {IconCellRendererComponent} from '../shared/cell-renderer/icon-cell-renderer/icon-cell-renderer.component';
import {
  MatChipsCellRendererComponent
} from '../shared/cell-renderer/mat-chips-cell-renderer/mat-chips-cell-renderer.component';
import {GenDto} from '../shared/models/genDto.model';
import {RowTemplateDto} from '../shared/models/rowTemplateDto.model';
import {GenerateService} from '../shared/services/generate.service';
import {DataOutputComponent} from "../data-output/data-output.component";

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {
  @ViewChild(DataOutputComponent) dataOutput!: DataOutputComponent;

  gridApi!: GridApi;

  generatedData: string = "";
  columnDefs: ColDef[] = [
    {field: '', sortable: false, rowDrag: true, editable: false, cellStyle: {'padding': '1rem 0 0 0'}, maxWidth: 42},
    {
      field: '',
      checkboxSelection: true,
      sortable: false,
      editable: false,
      cellStyle: {'padding': '1rem 0 0 0'},
      maxWidth: 20
    },
    {field: 'dataType', headerName: 'Datatype', cellEditor: AutocompleteCellEditorComponent},
    {field: 'name', headerName: 'Name'},
    {field: 'example', headerName: 'Example'},
    {
      field: 'option',
      headerName: 'Option',
      cellRenderer: MatChipsCellRendererComponent,
      cellEditor: MatChipsCellEditorComponent
    },
    {field: 'input', headerName: 'Input'},
    {field: 'regex', headerName: 'Regex'},
    {
      field: '',
      headerName: '',
      cellRenderer: IconCellRendererComponent,
      editable: false,
      cellStyle: {'padding': '0'},
      sortable: false,
      maxWidth: 42,
      cellRendererParams: {iconName: 'delete'}
    },
  ];
  defaultColDef: ColDef = {
    lockPosition: true,
    editable: true,
    resizable: true,
    sortable: true,
    autoHeight: true
  }
  rowData = [
    new RowTemplateDto('First name', 'name', 'John', [], 'www.deinname.ch/name', ''),
    new RowTemplateDto('Last name', 'nachname', 'Smith', [], '-', '')
  ];

  constructor(private generateService: GenerateService) {
  }

  ngOnInit(): void {
  }

  onGridReady(params: GridReadyEvent) {
    this.gridApi = params.api;
    this.gridApi.sizeColumnsToFit();
  }

  addNewRow() {
    this.gridApi.applyTransaction({add: [RowTemplateDto.createEmptyRowTemplateDto()]});
  }

  generate() {
    let genDto = this.createGenDto();
    this.generateService.generateTemplate(genDto).subscribe(res => {
      this.generatedData = res
    });
  }

  private createGenDto(): GenDto {
    let genDto = new GenDto();
    genDto.output = this.dataOutput.output;
    this.gridApi.forEachNode(node => {
      genDto.templateDto.rowTemplateDtoSet.push(node.data as RowTemplateDto);
    });
    return genDto;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.gridApi.sizeColumnsToFit();
  }

  get invalidRows() {
    let invalid = false;
    this.gridApi.forEachNode((node) => {
      let rowTemplateDto = node.data as RowTemplateDto;
      if (_.isEmpty(rowTemplateDto.name) || _.isEmpty(rowTemplateDto.dataType)) {
        invalid = true;
        return;
      }
    });
    return invalid;
  }
}
