import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcapteurComponent } from './addcapteur.component';

describe('AddcapteurComponent', () => {
  let component: AddcapteurComponent;
  let fixture: ComponentFixture<AddcapteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddcapteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddcapteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
