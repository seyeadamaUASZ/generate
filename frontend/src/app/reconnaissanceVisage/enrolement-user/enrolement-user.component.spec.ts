import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolementUserComponent } from './enrolement-user.component';

describe('EnrolementUserComponent', () => {
  let component: EnrolementUserComponent;
  let fixture: ComponentFixture<EnrolementUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnrolementUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrolementUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
