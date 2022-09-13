import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigurerWorkflowComponent } from './configurer-workflow.component';

describe('ConfigurerWorkflowComponent', () => {
  let component: ConfigurerWorkflowComponent;
  let fixture: ComponentFixture<ConfigurerWorkflowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfigurerWorkflowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigurerWorkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
