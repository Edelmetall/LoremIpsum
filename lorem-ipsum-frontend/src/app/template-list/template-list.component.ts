import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import * as _ from 'lodash';
import { map, Observable, startWith } from 'rxjs';
import { ConfirmDialogComponent } from '../shared/dialogs/confirm-dialog/confirm-dialog.component';
import { StorageHelper } from '../shared/helpers/storage-helper';
import { TemplateDto } from '../shared/models/templateDto.model';
import { SnackBarService } from '../shared/services/snackBar.service';
import { TemplateService } from '../shared/services/template.service';

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
  sharedFilteredTemplates!: Observable<Template[]>;
  loading = true;

  constructor(private templateService: TemplateService, private router: Router, private snackBarService: SnackBarService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadTemplates();
    this.setupFilter();
  }

  setupFilter() {
    this.filteredTemplates = this.templateFilter.valueChanges.pipe(
      startWith(''),
      map(value => this.filterTemplates(this.templates, value)),
    );
    this.sharedFilteredTemplates = this.templateFilter.valueChanges.pipe(
      startWith(''),
      map(value => this.filterTemplates(this.sharedTemplates, value)),
    );

  }

  loadTemplates() {
    this.templateService.getAllTemplates().subscribe((templates: TemplateDto[]) => {
      const currentUserId = StorageHelper.getCurrentUserId();
      _.forEach(templates, template => {
        const tModel = { id: template.id, name: template.name, desc: template.rowTemplateDtoSet.map(row => row.dataType).join(', ') } as Template;
        if (template.ownerId === currentUserId) {
          this.templates.push(tModel);
        } else {
          this.sharedTemplates.push(tModel);
        }
      });
      this.templateFilter.setValue('');
      this.loading = false;
    });
  }

  openConfirmDialog(template: Template): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { text: `Do you really want to delete the template: '${template.name}'` }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteTemplate(template);
      }
    });
  }

  editTemplate(id: bigint) {
    this.router.navigate(['/template', id]);
  }

  deleteTemplate(template: Template) {
    this.templateService.deleteTemplate(template.id).subscribe((deleted: boolean) => {
      if (deleted) {
        this.snackBarService.info(`Template '${template.name}' deleted`);
        this.removeTemplate(template);
      } else {
        this.snackBarService.error(`Error during deletion of template '${template.name}'`);
      }
    });
  }

  private removeTemplate(template: Template) {
    let index = this.templates.indexOf(template);
    if (index > -1) {
      this.templates.splice(index, 1);
    } else {
      index = this.sharedTemplates.indexOf(template);
      if (index > -1) {
        this.sharedTemplates.splice(index, 1);
      }
    }
    this.templateFilter.setValue(this.templateFilter.value);
  }

  private filterTemplates(templates: Template[], value: string): Template[] {
    return _.filter(templates, template => this.contains(template.name, value) || this.contains(template.desc, value));
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
