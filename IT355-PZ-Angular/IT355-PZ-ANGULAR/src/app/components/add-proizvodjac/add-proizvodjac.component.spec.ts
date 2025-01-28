import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProizvodjacComponent } from './add-proizvodjac.component';

describe('AddProizvodjacComponent', () => {
  let component: AddProizvodjacComponent;
  let fixture: ComponentFixture<AddProizvodjacComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddProizvodjacComponent]
    });
    fixture = TestBed.createComponent(AddProizvodjacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
