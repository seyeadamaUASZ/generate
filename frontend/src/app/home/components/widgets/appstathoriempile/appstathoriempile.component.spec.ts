import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppstathoriempileComponent } from './appstathoriempile.component';

describe('AppstathoriempileComponent', () => {
  let component: AppstathoriempileComponent;
  let fixture: ComponentFixture<AppstathoriempileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppstathoriempileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppstathoriempileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
