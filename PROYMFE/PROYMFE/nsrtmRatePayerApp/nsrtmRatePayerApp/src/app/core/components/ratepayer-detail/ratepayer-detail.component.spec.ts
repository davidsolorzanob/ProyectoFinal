import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatepayerDetailComponent } from './ratepayer-detail.component';

describe('RatepayerDetailComponent', () => {
  let component: RatepayerDetailComponent;
  let fixture: ComponentFixture<RatepayerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatepayerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatepayerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
