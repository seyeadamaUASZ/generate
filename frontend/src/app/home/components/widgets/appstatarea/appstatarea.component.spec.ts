import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppstatareaComponent } from './appstatarea.component';

describe('AppstatareaComponent', () => {
  let component: AppstatareaComponent;
  let fixture: ComponentFixture<AppstatareaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppstatareaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppstatareaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
