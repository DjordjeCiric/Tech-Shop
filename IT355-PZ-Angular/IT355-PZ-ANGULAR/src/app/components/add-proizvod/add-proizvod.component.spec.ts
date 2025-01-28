import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProizvodComponent } from './add-proizvod.component';

describe('AddProizvodComponent', () => {
  let component: AddProizvodComponent;
  let fixture: ComponentFixture<AddProizvodComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddProizvodComponent]
    });
    fixture = TestBed.createComponent(AddProizvodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
