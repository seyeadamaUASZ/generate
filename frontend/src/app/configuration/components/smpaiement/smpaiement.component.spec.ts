import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmpaiementComponent } from './smpaiement.component';

describe('SmpaiementComponent', () => {
  let component: SmpaiementComponent;
  let fixture: ComponentFixture<SmpaiementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmpaiementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmpaiementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
