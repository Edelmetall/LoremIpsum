import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { GridApi, GridReadyEvent } from 'ag-grid-community';
import * as _ from 'lodash';
import { GenDto } from '../shared/models/genDto.model';
import { RowTemplateDto } from '../shared/models/rowTemplateDto.model';
import { TemplateService } from '../shared/services/template.service';
import { DataOutputComponent } from "../data-output/data-output.component";
import { TemplateDto } from '../shared/models/templateDto.model';
import { ActivatedRoute, Router } from '@angular/router';
import { GridConfig } from './model';
import { SnackBarService } from '../shared/services/snackBar.service';
import { StorageHelper } from '../shared/helpers/storage-helper';

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

  constructor(private route: ActivatedRoute, private templateService: TemplateService, private snackBarService: SnackBarService, private router: Router) {
  }

  ngOnInit(): void {
    this.templateService.getAvailableDataFormats().subscribe((dataFormats: any) => {
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
      this.templateService.getTemplateById(Number(templateId)).subscribe((templateDto: TemplateDto) => {
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
    this.templateService.generateTemplate(genDto).subscribe(res => {
      this.generatedData = res
    });
  }

  saveTemplate() {
    this.templateDto.rowTemplateDtoSet = this.createRowTemplateDtoSet();
    if (!_.isNil(this.currentUserId)) {
      if(this.templateDto.ownerId !== this.currentUserId){
        this.templateDto.id = undefined;
        this.templateDto.rowTemplateDtoSet.forEach(row=>row.id = undefined)
      }
      this.templateDto.ownerId = this.currentUserId;
    }
    this.templateService.saveTemplate(this.templateDto).subscribe(res => {
      this.templateDto = res;
      this.snackBarService.info(`Template '${this.templateDto.name}' saved`);
      this.router.navigate(['/template', res.id]);
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

  get isLoggedIn(): boolean {
    return !_.isNil(this.currentUserId);
  }

  get currentUserId(): bigint | undefined {
    return StorageHelper.getCurrentUserId();
  }

  get isTemplateOwner(): boolean {
    return this.templateDto.ownerId === this.currentUserId || this.templateDto.ownerId === undefined;
  }
}
