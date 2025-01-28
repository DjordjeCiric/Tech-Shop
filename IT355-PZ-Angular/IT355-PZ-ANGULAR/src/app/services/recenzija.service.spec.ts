import { TestBed } from '@angular/core/testing';

import { RecenzijaService } from './recenzija.service';

describe('RecenzijaService', () => {
  let service: RecenzijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecenzijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
