import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmlangueComponent } from './smlangue.component';

describe('SmlangueComponent', () => {
  let component: SmlangueComponent;
  let fixture: ComponentFixture<SmlangueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmlangueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmlangueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
