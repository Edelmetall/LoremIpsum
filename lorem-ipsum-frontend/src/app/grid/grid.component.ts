import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { GridApi, GridReadyEvent } from 'ag-grid-community';
import * as _ from 'lodash';
import { GenDto } from '../shared/models/genDto.model';
import { RowTemplateDto } from '../shared/models/rowTemplateDto.model';
import { GenerateService } from '../shared/services/generate.service';
import { DataOutputComponent } from "../data-output/data-output.component";
import { TemplateDto } from '../shared/models/templateDto.model';
import { ActivatedRoute } from '@angular/router';
import { GridConfig } from './model';
import { SnackBarService } from '../shared/services/snackBar.service';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {
  @ViewChild(DataOutputComponent) dataOutput!: DataOutputComponent;

  gridApi!: GridApi;
  templateDto: TemplateDto = new TemplateDto();
  generatedData: string = "";
  columnDefs = GridConfig.columnDefs;
  defaultColDef = GridConfig.defaultColDef;
  dataLoaded = false;

  rowData: RowTemplateDto[] = [];

  constructor(private route: ActivatedRoute, private generateService: GenerateService, private snackBarService: SnackBarService) {
  }

  ngOnInit(): void {
    this.generateService.getAvailableDataFormats().subscribe((dataFormats: any) => {
      GridConfig.dataTypeColDef.cellEditorParams.options = _.map(dataFormats, d => d.name);
      console.log(GridConfig.dataTypeColDef.cellEditorParams);
      this.dataLoaded = true;
    });
  }

  onGridReady(params: GridReadyEvent) {
    this.gridApi = params.api;
    this.gridApi.sizeColumnsToFit();

    const templateId = this.route.snapshot.paramMap.get('templateId');
    if (!_.isNil(templateId)) {
      this.generateService.getTemplateById(Number(templateId)).subscribe((templateDto: TemplateDto) => {
        this.templateDto = templateDto;
        this.addRows(templateDto.rowTemplateDtoSet as RowTemplateDto[]);
      });
    } else {
      this.addRows();
    }
  }

  addRows(rows: RowTemplateDto[] = [RowTemplateDto.createEmptyRowTemplateDto()]) {
    this.gridApi.applyTransaction({ add: rows });
  }

  generate() {
    let genDto = new GenDto(this.dataOutput.output, this.createRowTemplateDtoSet());
    this.generateService.generateTemplate(genDto).subscribe(res => {
      this.generatedData = res
    });
  }

  saveTemplate() {
    this.templateDto.rowTemplateDtoSet = this.createRowTemplateDtoSet();
    this.generateService.saveTemplate(this.templateDto).subscribe(res => {
      this.templateDto = res;
      this.snackBarService.info(`Template '${this.templateDto.name}' saved`);
    });
  }

  private createRowTemplateDtoSet() {
    const rowTemplateDtoSet = new Array<RowTemplateDto>();
    let index = 0;
    this.gridApi.forEachNode(node => {
      const rowTemplateDto = node.data as RowTemplateDto;
      rowTemplateDto.index = index++;
      rowTemplateDtoSet.push(rowTemplateDto);
    });
    return rowTemplateDtoSet;
  }


  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.gridApi.sizeColumnsToFit();
  }

  get invalidRows() {
    let invalid = this.gridApi.getDisplayedRowCount() <= 0;
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
