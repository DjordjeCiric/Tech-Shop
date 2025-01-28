import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Proizvodjac } from 'src/app/models/proizvodjac';
import { ProizvodService } from 'src/app/services/proizvod.service';

@Component({
  selector: 'app-add-proizvodjac',
  templateUrl: './add-proizvodjac.component.html',
  styleUrls: ['./add-proizvodjac.component.css']
})
export class AddProizvodjacComponent implements OnInit {
  addProizvodjacForm: FormGroup;

  constructor(private fb: FormBuilder, private proizvodService: ProizvodService) {
    this.addProizvodjacForm = this.fb.group({
      proizvodjacIme: ['', Validators.required],
      opis: ['', Validators.required],
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.addProizvodjacForm.valid) {
      const formValues = this.addProizvodjacForm.value;
      const newProizvodjac: Proizvodjac = {
        proizvodjacIme: formValues.proizvodjacIme,
        opis: formValues.opis,
        id: 0
      };
      this.proizvodService.addProizvodjac(newProizvodjac).subscribe(
        response => {
          console.log('Proizvodjac uspesno dodat', response);
          this.addProizvodjacForm.reset();
        },
        error => {
          console.error('Error adding proizvodjac', error);
        }
      );
    }
  }
}
