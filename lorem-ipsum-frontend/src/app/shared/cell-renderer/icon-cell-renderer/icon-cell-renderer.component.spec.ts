import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconCellRendererComponent } from './icon-cell-renderer.component';

describe('IconCellRendererComponent', () => {
  let component: IconCellRendererComponent;
  let fixture: ComponentFixture<IconCellRendererComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IconCellRendererComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IconCellRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
