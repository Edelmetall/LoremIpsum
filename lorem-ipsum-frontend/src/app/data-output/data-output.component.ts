import {Component, Input, OnInit} from '@angular/core';
import {Clipboard} from '@angular/cdk/clipboard';
import {OutputEnum} from './model';
import {MatTabChangeEvent} from "@angular/material/tabs";
import {NotificationService} from '../shared/services/notification.service';
import {CommunicationService} from '../shared/services/communication.service';
import {OptionDto} from "../shared/models/optionDto.model";

@Component({
  selector: 'app-data-output',
  templateUrl: './data-output.component.html',
  styleUrls: ['./data-output.component.scss']
})
export class DataOutputComponent implements OnInit {

  @Input()
  generatedData!: string;

  outputEnum = OutputEnum.XML;
  outputOption: string | undefined
  tab: any | undefined;
  tabs = [
    {
      outputEnum: OutputEnum.XML, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.JSON, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.JAVA, delimiter: undefined,
      delimiterOptions: [{displayValue: 'Class', value: 'Class'},{displayValue: 'Record', value: 'Record'}]
    },
    {
      outputEnum: OutputEnum.SQL, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.PHP, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.CSHARP, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.CSV, delimiter: undefined, delimiterOptions: undefined
    }
  ];

  constructor(private notificationService: NotificationService,
              private clipboard: Clipboard,
              private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
  }

  copyData() {
    this.clipboard.copy(this.generatedData);
    this.notificationService.info('Data copied');
  }

  download() {
    const element = document.createElement('a');
    const fileType = this.outputEnum === OutputEnum.JSON ? 'text/json' : 'text/plain';
    element.setAttribute('href', `data:${fileType};charset=utf-8,${encodeURIComponent(this.generatedData)}`);
    element.setAttribute('download', `template.${this.outputEnum.displayedText.toLowerCase()}`);

    var event = new MouseEvent("click");
    element.dispatchEvent(event);
    element.remove();
  }

  public optionChange(){
    this.outputOption = this.tab.delimiter;
    this.communicationService.notifyOutputTabChanged();
  }

  public tabChanged(tabChangeEvent: MatTabChangeEvent) {
    this.tab = this.tabs[tabChangeEvent.index];
    this.outputEnum = this.tabs[tabChangeEvent.index].outputEnum;
    this.communicationService.notifyOutputTabChanged();
  }
}
