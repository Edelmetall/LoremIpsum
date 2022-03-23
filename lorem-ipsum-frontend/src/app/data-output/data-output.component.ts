import { Component, OnInit, PipeTransform } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Clipboard } from '@angular/cdk/clipboard';
import { PrettyXmlPipe } from '../shared/pipes/pretty-xml.pipe';
import { JsonPipe } from '@angular/common';
import { Response, TabLabels } from './model';
import * as _ from 'lodash';

@Component({
  selector: 'app-data-output',
  templateUrl: './data-output.component.html',
  providers: [PrettyXmlPipe, JsonPipe],
  styleUrls: ['./data-output.component.scss']
})
export class DataOutputComponent implements OnInit {

  response?: Response[];
  constructor(private _snackBar: MatSnackBar, private clipboard: Clipboard, private xmlPipe: PrettyXmlPipe, private jsonPipe: JsonPipe) {
    this.response = [
      {
        "data": [
          {
            "attrName": "vorname",
            "attrValue": "John"
          },
          {
            "attrName": "nachname",
            "attrValue": "Smith"
          },
          {
            "attrName": "iban",
            "attrValue": "DE33 5013 0600 0123 4567 89"
          }
        ]
      },
      {
        "data": [
          {
            "attrName": "vorname",
            "attrValue": "Nils"
          },
          {
            "attrName": "nachname",
            "attrValue": "Muster"
          },
          {
            "attrName": "iban",
            "attrValue": "DE98 5012 0500 0123 4567 89"
          }
        ]
      }
    ];
  }

  ngOnInit(): void {
  }

  tabs = [{
    label: TabLabels.JSON, pipe: this.jsonPipe, builder: this.buildJson.bind(this)
  }, {
    label: TabLabels.XML, pipe: this.xmlPipe, builder: this.buildXml.bind(this)
  }, {
    label: TabLabels.CSV, pipe: null, builder: this.buildCsv.bind(this), delimiterOptions: [{ value: ',', displayValue: 'Komma' }, { value: ';', displayValue: 'Semicolon' }], delimiter: ','
  }
  ];


  buildJson(response: Response[]) {
    if (_.isNil(response)) return '';
    let json = '[';
    let responseLength = 0;
    let sizeOfResponse = response.length;
    _.forEach(response, res => {
      json += '{';
      let dataLength = 0;
      _.forEach(res.data, data => {
        json += `"${data.attrName}":"${data.attrValue}"${dataLength < res.data.length - 1 ? ',' : ''}`;
        dataLength++;
      })
      json += `}${responseLength < sizeOfResponse - 1 ? ',' : ''}`;
      responseLength++;
    })
    json += ']';
    return JSON.parse(json);
  }

  buildXml(response: Response[]) {
    if (_.isNil(response)) return '';
    let xml = '<?xml version="1.0" encoding="UTF-8"?><data>';
    _.forEach(response, res => {
      xml += '<entry>';
      _.forEach(res.data, data => {
        xml += `<${data.attrName}>${data.attrValue}</${data.attrName}>`;
      })
      xml += '</entry>';
    })
    xml += '</data>';
    return xml;
  }

  buildCsv(response: Response[]) {
    if (_.isNil(response)) return '';
    let delimiter = this.csvTab.delimiter;
    let csv = `${response[0].data.map(data => data.attrName).join(delimiter)}\n`;
    _.forEach(response, res => {
      csv += `${res.data.map(data => data.attrValue).join(delimiter)}\n`;
    })
    return csv;
  }

  copyData(pipe: PipeTransform | null, builder: any) {
    this.clipboard.copy(this.getFormattedOutput(pipe, builder));
    this.showSnackBar();
  }

  showSnackBar() {
    this._snackBar.open('Data copied', '', {
      duration: 3000
    })
  }

  getFormattedOutput(pipe: PipeTransform | null, builder: any) {
    if (_.isNil(this.response)) return '';
    let values = builder(this.response);
    return !_.isNil(pipe) ? pipe.transform(values) : values;
  }

  get csvTab(): any {
    return this.getTabByLabel(TabLabels.CSV)
  }

  get xmlTab(): any {
    return this.getTabByLabel(TabLabels.XML);
  }

  get jsonTab(): any {
    return this.getTabByLabel(TabLabels.JSON);
  }

  private getTabByLabel(label: string): any {
    return this.tabs.find(tab => tab.label === label);
  }

}
