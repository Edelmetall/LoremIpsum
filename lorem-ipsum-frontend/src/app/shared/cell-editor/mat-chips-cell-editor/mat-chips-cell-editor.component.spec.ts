import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatChipsCellEditorComponent } from './mat-chips-cell-editor.component';

describe('MatChipsCellEditorComponent', () => {
  let component: MatChipsCellEditorComponent;
  let fixture: ComponentFixture<MatChipsCellEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatChipsCellEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatChipsCellEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
