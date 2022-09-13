import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SminscriptionComponent } from './sminscription.component';

describe('SminscriptionComponent', () => {
  let component: SminscriptionComponent;
  let fixture: ComponentFixture<SminscriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SminscriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SminscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
