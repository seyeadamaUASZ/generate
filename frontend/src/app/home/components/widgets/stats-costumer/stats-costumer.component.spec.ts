import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatsCostumerComponent } from './stats-costumer.component';

describe('StatsCostumerComponent', () => {
  let component: StatsCostumerComponent;
  let fixture: ComponentFixture<StatsCostumerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatsCostumerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatsCostumerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
