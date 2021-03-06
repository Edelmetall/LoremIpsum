import {Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CellValueChangedEvent, GridApi, GridReadyEvent, NewValueParams} from 'ag-grid-community';
import * as _ from 'lodash';
import {GenDto} from '../shared/models/genDto.model';
import {RowTemplateDto} from '../shared/models/rowTemplateDto.model';
import {TemplateService} from '../shared/services/template.service';
import {DataOutputComponent} from "../data-output/data-output.component";
import {TemplateDto} from '../shared/models/templateDto.model';
import {ActivatedRoute, Router} from '@angular/router';
import {GridConfig} from './model';
import {NotificationService} from '../shared/services/notification.service';
import {StorageHelper} from '../shared/helpers/storage-helper';
import {CommunicationService} from '../shared/services/communication.service';
import {finalize, Subscription} from 'rxjs';
import {OutputEnum} from "../data-output/model";
import {OptionDto} from "../shared/models/optionDto.model";

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit, OnDestroy {
  @ViewChild(DataOutputComponent) dataOutput!: DataOutputComponent;
  outputTabSubscription!: Subscription;
  gridApi!: GridApi;
  templateDto: TemplateDto = new TemplateDto();
  generatedData: string = "";
  columnDefs = GridConfig.columnDefs;
  defaultColDef = GridConfig.defaultColDef;
  availableDataFormats!: { name: string, options: any }[];
  clickListener!: any;
  dataLoaded = false;

  rowData: RowTemplateDto[] = [];

  constructor(private route: ActivatedRoute,
              private templateService: TemplateService,
              private notificationService: NotificationService,
              private router: Router,
              private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
    this.clickListener = this.stopEditingWhenCellsLoseFocus.bind(this)
    window.addEventListener('click', this.clickListener);
    this.outputTabSubscription = this.communicationService.onOutputTabChanged().subscribe(() => {
      this.generate();
    });
    this.communicationService.notifyLoading(true)
    this.templateService.getAvailableDataFormats()
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: (dataFormats: any) => {
          this.availableDataFormats = dataFormats;
          GridConfig.dataTypeColDef.cellEditorParams.options = _.map(dataFormats, d => d.name);
          GridConfig.dataTypeColDef.onCellValueChanged = this.onDataTypeChanged.bind(this)
          GridConfig.optionColDef.cellEditorParams.options = dataFormats;
          console.log(GridConfig.dataTypeColDef.cellEditorParams);
          this.dataLoaded = true;
        },
        error: () => this.notificationService.error('Config could not be loaded')
      });
  }

  ngOnDestroy(): void {
    this.outputTabSubscription?.unsubscribe();
    window.removeEventListener('click', this.clickListener);
  }

  onGridReady(params: GridReadyEvent) {
    this.gridApi = params.api;
    this.gridApi.sizeColumnsToFit();

    const templateId = this.route.snapshot.paramMap.get('templateId');
    if (!_.isNil(templateId)) {
      this.communicationService.notifyLoading(true);
      this.templateService.getTemplateById(Number(templateId))
        .pipe(finalize(() => this.communicationService.notifyLoading(false)))
        .subscribe({
          next: (templateDto: TemplateDto) => {
            this.templateDto = templateDto;
            this.addRows(templateDto.rowTemplateDtoSet as RowTemplateDto[]);
          },
          error: () => this.notificationService.error('Template could not be loaded')
        });
    } else {
      this.addRows();
    }
  }

  onDataTypeChanged(params: any) {
    if (params.newValue !== params.oldValue) {
      (params.node.data as RowTemplateDto).option = [];
      this.gridApi.applyTransaction({update: [params.node.data]})
    }
  }

  stopEditingWhenCellsLoseFocus(e: any) {
    const grid = document.getElementById('grid');
    if (!grid?.contains(e.target) && !document.getElementsByClassName('mat-select-panel')[0]?.contains(e.target)) {
      this.gridApi?.stopEditing();
    }
  }

  addRows(rows: RowTemplateDto[] = [RowTemplateDto.createEmptyRowTemplateDto()]) {
    this.gridApi.applyTransaction({add: rows});
  }

  generate() {
    this.communicationService.notifyLoading(true);
    let outputName = this.dataOutput ? this.dataOutput.outputEnum.name : OutputEnum.XML.name;

    let genDto;
    if(this.dataOutput?.outputOption && this.dataOutput.outputOption[0]){
      let output = new OptionDto();
      output.optionEnum = 'JAVA_VERSION';
      output.optionData = this.dataOutput.outputOption;
      genDto = new GenDto(outputName, this.createRowTemplateDtoSet(), output);
    }else {
      genDto = new GenDto(outputName, this.createRowTemplateDtoSet());
    }

    this.templateService.generateTemplate(genDto)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          this.generatedData = res
        },
        error: err => this.notificationService.error(err)
      });
  }

  saveTemplate() {
    this.templateDto.rowTemplateDtoSet = this.createRowTemplateDtoSet();
    if (!_.isNil(this.currentUserId)) {
      if (this.templateDto.ownerId !== this.currentUserId) {
        this.templateDto.id = undefined;
        this.templateDto.rowTemplateDtoSet.forEach(row => row.id = undefined)
      }
      this.templateDto.ownerId = this.currentUserId;
    }
    this.communicationService.notifyLoading(true);
    this.templateService.saveTemplate(this.templateDto)
      .pipe(finalize(() => this.communicationService.notifyLoading(false)))
      .subscribe({
        next: res => {
          this.templateDto = res;
          this.notificationService.info(`Template '${this.templateDto.name}' saved`);
          this.router.navigate(['/template', res.id]);
        },
        error: () => this.notificationService.error('Template could not be saved')
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
    this.gridApi?.sizeColumnsToFit();
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
    return StorageHelper.getCustomerId();
  }

  get isTemplateOwner(): boolean {
    return this.templateDto.ownerId === this.currentUserId || this.templateDto.ownerId === undefined;
  }
}
