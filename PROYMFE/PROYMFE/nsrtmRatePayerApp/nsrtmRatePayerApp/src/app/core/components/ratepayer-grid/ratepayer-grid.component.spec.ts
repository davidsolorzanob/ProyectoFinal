import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatepayerGridComponent } from './ratepayer-grid.component';

describe('RatepayerGridComponent', () => {
  let component: RatepayerGridComponent;
  let fixture: ComponentFixture<RatepayerGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatepayerGridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatepayerGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
