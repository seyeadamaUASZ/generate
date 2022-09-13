import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppstatbulleComponent } from './appstatbulle.component';

describe('AppstatbulleComponent', () => {
  let component: AppstatbulleComponent;
  let fixture: ComponentFixture<AppstatbulleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppstatbulleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppstatbulleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
