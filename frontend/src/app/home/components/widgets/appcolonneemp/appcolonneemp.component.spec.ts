import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppcolonneempComponent } from './appcolonneemp.component';

describe('AppcolonneempComponent', () => {
  let component: AppcolonneempComponent;
  let fixture: ComponentFixture<AppcolonneempComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppcolonneempComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppcolonneempComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
