import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiSelectCellEditorComponent } from './multi-select-cell-editor.component';

describe('MultiSelectCellEditorComponent', () => {
  let component: MultiSelectCellEditorComponent;
  let fixture: ComponentFixture<MultiSelectCellEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultiSelectCellEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiSelectCellEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
