import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmmenuComponent } from './smmenu.component';

describe('SmmenuComponent', () => {
  let component: SmmenuComponent;
  let fixture: ComponentFixture<SmmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
