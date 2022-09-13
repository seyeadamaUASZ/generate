import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonneecapteursComponent } from './donneecapteurs.component';

describe('DonneecapteursComponent', () => {
  let component: DonneecapteursComponent;
  let fixture: ComponentFixture<DonneecapteursComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonneecapteursComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonneecapteursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
