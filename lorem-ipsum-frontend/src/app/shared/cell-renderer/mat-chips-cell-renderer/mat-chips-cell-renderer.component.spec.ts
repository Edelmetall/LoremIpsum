import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatChipsCellRendererComponent } from './mat-chips-cell-renderer.component';

describe('MatChipsCellRendererComponent', () => {
  let component: MatChipsCellRendererComponent;
  let fixture: ComponentFixture<MatChipsCellRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatChipsCellRendererComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatChipsCellRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
