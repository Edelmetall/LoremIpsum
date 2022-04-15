import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import * as _ from 'lodash';
import { map, Observable, startWith } from 'rxjs';
import { TemplateDto } from '../shared/models/templateDto.model';
import { GenerateService } from '../shared/services/generate.service';

@Component({
  selector: 'app-template-list',
  templateUrl: './template-list.component.html',
  styleUrls: ['./template-list.component.scss']
})
export class TemplateListComponent implements OnInit {
  templates: Template[] = [];
  sharedTemplates: Template[] = [];

  templateFilter = new FormControl();
  filteredTemplates!: Observable<Template[]>;
  loading = true;

  constructor(private generateService: GenerateService, private router: Router) { }

  ngOnInit(): void {
    this.loadTemplates();
    this.filteredTemplates = this.templateFilter.valueChanges.pipe(
      startWith(''),
      map(value => this.filterTemplates(value)),
    );
  }

  loadTemplates() {
    this.generateService.getAllTemplates().subscribe((templates: TemplateDto[]) => {
      _.forEach(templates, template => {
        const tModel = { id: template.id, name: template.name, desc: template.rowTemplateDtoSet.map(row => row.dataType).join(', ') } as Template;
        this.templates.push(tModel);
      });
      this.templateFilter.setValue('');
      this.loading = false;
    });
  }

  editTemplate(id: bigint) {
    this.router.navigate(['/template', id]);
  }

  private filterTemplates(value: string): Template[] {
    return _.filter(this.templates, template => this.contains(template.name, value) || this.contains(template.desc, value));
  }

  private contains(text: string, searchValue: string): boolean {
    return text.toLowerCase().indexOf(searchValue.toLowerCase()) > -1;
  }

}

type Template = {
  id: bigint;
  name: string;
  desc: string;
  favorite: boolean;
}
