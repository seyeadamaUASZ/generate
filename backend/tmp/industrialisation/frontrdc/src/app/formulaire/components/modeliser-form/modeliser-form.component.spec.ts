import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeliserFormComponent } from './modeliser-form.component';

describe('ModeliserFormComponent', () => {
  let component: ModeliserFormComponent;
  let fixture: ComponentFixture<ModeliserFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeliserFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeliserFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
