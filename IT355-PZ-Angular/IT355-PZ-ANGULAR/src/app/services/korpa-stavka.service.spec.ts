import { TestBed } from '@angular/core/testing';

import { KorpaStavkaService } from './korpa-stavka.service';

describe('KorpaStavkaService', () => {
  let service: KorpaStavkaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KorpaStavkaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
