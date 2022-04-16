import {Component, Input, OnInit} from '@angular/core';
import {Clipboard} from '@angular/cdk/clipboard';
import {PrettyXmlPipe} from '../shared/pipes/pretty-xml.pipe';
import {JsonPipe} from '@angular/common';
import {OutputEnum} from './model';
import {MatTabChangeEvent} from "@angular/material/tabs";
import {SnackBarService} from '../shared/services/snackBar.service';
import {CommunicationService} from '../shared/services/communication.service';

@Component({
  selector: 'app-data-output',
  templateUrl: './data-output.component.html',
  providers: [PrettyXmlPipe, JsonPipe],
  styleUrls: ['./data-output.component.scss']
})
export class DataOutputComponent implements OnInit {

  @Input()
  generatedData!: string;

  outputEnum = OutputEnum.XML;
  tabs = [
    {
      outputEnum: OutputEnum.XML, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.JSON, delimiter: undefined, delimiterOptions: undefined
    },
    {
      outputEnum: OutputEnum.JAVA, delimiter: undefined, delimiterOptions: undefined
    }
  ];

  constructor(private snackBarService: SnackBarService, private clipboard: Clipboard, private communicationService: CommunicationService) {
  }

  ngOnInit(): void {
  }

  copyData() {
    this.clipboard.copy(this.generatedData);
    this.snackBarService.info('Data copied');
  }

  public tabChanged(tabChangeEvent: MatTabChangeEvent) {
    this.outputEnum = this.tabs[tabChangeEvent.index].outputEnum;
    this.communicationService.notifyOutputTabChanged();
  }
}
