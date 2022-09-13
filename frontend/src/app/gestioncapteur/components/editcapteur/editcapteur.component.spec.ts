import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditcapteurComponent } from './editcapteur.component';

describe('EditcapteurComponent', () => {
  let component: EditcapteurComponent;
  let fixture: ComponentFixture<EditcapteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditcapteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditcapteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
