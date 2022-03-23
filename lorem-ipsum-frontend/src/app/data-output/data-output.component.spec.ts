import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataOutputComponent } from './data-output.component';

describe('DataOutputComponent', () => {
  let component: DataOutputComponent;
  let fixture: ComponentFixture<DataOutputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataOutputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataOutputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
