import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-template-list',
  templateUrl: './template-list.component.html',
  styleUrls: ['./template-list.component.scss']
})
export class TemplateListComponent implements OnInit {
  templates: Template[] = [];
  sharedTemplates: Template[] = [];

  constructor() { }

  ngOnInit(): void {
    this.templates = [
      {
        name: 'Your template',
        desc: 'vorname, nachname, iban',
        favorite: false
      }
    ];
    this.sharedTemplates = [
      {
        name: 'Personal information',
        desc: 'first name, last name, email address',
        favorite: true
      },
      {
        name: 'Address',
        desc: 'first name, last name, country, region, city, street',
        favorite: false
      }
    ];
  }
}

type Template = {
  name: string;
  desc: string;
  favorite: boolean;
}
