import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutocompleteCellEditorComponent } from './autocomplete-cell-editor.component';

describe('AutocompleteCellEditorComponent', () => {
  let component: AutocompleteCellEditorComponent;
  let fixture: ComponentFixture<AutocompleteCellEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutocompleteCellEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutocompleteCellEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
