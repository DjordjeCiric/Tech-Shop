import { TestBed } from '@angular/core/testing';

import { KorpaService } from './korpa.service';

describe('KorpaService', () => {
  let service: KorpaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KorpaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
