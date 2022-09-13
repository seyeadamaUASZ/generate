import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnroleUserComponent } from './enrole-user.component';

describe('EnroleUserComponent', () => {
  let component: EnroleUserComponent;
  let fixture: ComponentFixture<EnroleUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnroleUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnroleUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
