import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoirTransitionComponent } from './voir-transition.component';

describe('VoirTransitionComponent', () => {
  let component: VoirTransitionComponent;
  let fixture: ComponentFixture<VoirTransitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoirTransitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoirTransitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
