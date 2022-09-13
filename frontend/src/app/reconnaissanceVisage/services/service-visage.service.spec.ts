import { TestBed } from '@angular/core/testing';

import { ServiceVisageService } from './service-visage.service';

describe('ServiceVisageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceVisageService = TestBed.get(ServiceVisageService);
    expect(service).toBeTruthy();
  });
});
