import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmnotificationComponent } from './smnotification.component';

describe('SmnotificationComponent', () => {
  let component: SmnotificationComponent;
  let fixture: ComponentFixture<SmnotificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmnotificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmnotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
