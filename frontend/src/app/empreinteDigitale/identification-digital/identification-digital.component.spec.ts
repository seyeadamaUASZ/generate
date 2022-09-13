import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IdentificationDigitalComponent } from './identification-digital.component';

describe('IdentificationDigitalComponent', () => {
  let component: IdentificationDigitalComponent;
  let fixture: ComponentFixture<IdentificationDigitalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IdentificationDigitalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IdentificationDigitalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
