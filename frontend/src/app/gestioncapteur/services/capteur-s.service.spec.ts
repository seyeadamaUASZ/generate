import { TestBed } from '@angular/core/testing';

import { CapteurSService } from './capteur-s.service';

describe('CapteurSService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CapteurSService = TestBed.get(CapteurSService);
    expect(service).toBeTruthy();
  });
});
