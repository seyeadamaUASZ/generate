import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { App3dComponent } from './app3d.component';

describe('App3dComponent', () => {
  let component: App3dComponent;
  let fixture: ComponentFixture<App3dComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ App3dComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(App3dComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
