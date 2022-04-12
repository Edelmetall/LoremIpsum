import {Component, Input, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Clipboard} from '@angular/cdk/clipboard';
import {PrettyXmlPipe} from '../shared/pipes/pretty-xml.pipe';
import {JsonPipe} from '@angular/common';
import {TabLabels} from './model';
import {MatTabChangeEvent} from "@angular/material/tabs";

@Component({
  selector: 'app-data-output',
  templateUrl: './data-output.component.html',
  providers: [PrettyXmlPipe, JsonPipe],
  styleUrls: ['./data-output.component.scss']
})
export class DataOutputComponent implements OnInit {

  @Input()
  generatedData!: string;

  output = TabLabels.XML;
  tabs = [
    {
      label: TabLabels.XML, delimiter: undefined, delimiterOptions: undefined
    },
    {
      label: TabLabels.JSON, delimiter: undefined, delimiterOptions: undefined
    }
  ];

  constructor(private _snackBar: MatSnackBar, private clipboard: Clipboard) {
  }

  ngOnInit(): void {
  }

  copyData() {
    this.clipboard.copy(this.generatedData);
    this.showSnackBar();
  }

  showSnackBar() {
    this._snackBar.open('Data copied', '', {
      duration: 3000
    })
  }

  get xmlTab(): any {
    return this.getTabByLabel(TabLabels.XML);
  }

  private getTabByLabel(label: string): any {
    return this.tabs.find(tab => tab.label === label);
  }

  public tabChanged(tabChangeEvent: MatTabChangeEvent) {
    this.output = this.tabs[tabChangeEvent.index].label;
  }
}
