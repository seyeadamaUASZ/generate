import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppstathorizontalComponent } from './appstathorizontal.component';

describe('AppstathorizontalComponent', () => {
  let component: AppstathorizontalComponent;
  let fixture: ComponentFixture<AppstathorizontalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppstathorizontalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppstathorizontalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
