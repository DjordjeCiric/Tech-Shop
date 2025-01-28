import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProizvodDetailsComponent } from './proizvod-details.component';

describe('ProizvodDetailsComponent', () => {
  let component: ProizvodDetailsComponent;
  let fixture: ComponentFixture<ProizvodDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProizvodDetailsComponent]
    });
    fixture = TestBed.createComponent(ProizvodDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
