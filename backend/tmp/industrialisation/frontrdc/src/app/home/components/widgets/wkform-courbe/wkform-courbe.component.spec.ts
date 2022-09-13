import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WkformCourbeComponent } from './wkform-courbe.component';

describe('WkformCourbeComponent', () => {
  let component: WkformCourbeComponent;
  let fixture: ComponentFixture<WkformCourbeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WkformCourbeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WkformCourbeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
