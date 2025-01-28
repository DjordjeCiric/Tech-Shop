import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Proizvod } from 'src/app/models/proizvod';
import { Proizvodjac } from 'src/app/models/proizvodjac';
import { ProizvodService } from 'src/app/services/proizvod.service';
@Component({
  selector: 'app-add-proizvod',
  templateUrl: './add-proizvod.component.html',
  styleUrls: ['./add-proizvod.component.css']
})
export class AddProizvodComponent implements OnInit{
  addProizvodForm: FormGroup;
  proizvodjac: Proizvodjac[] = [];

  constructor(private fb: FormBuilder, private proizvodService: ProizvodService) {
    this.addProizvodForm = this.fb.group({
      proizvodIme: ['', Validators.required],
      proizvodImageUrl: ['', Validators.required],
      cena: ['', Validators.required],
      opis: ['', Validators.required],
      tip: ['', Validators.required],
      proizvodjac: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadProizvodjac();
  }

  loadProizvodjac(): void {
    this.proizvodService.getProizvodjac().subscribe(data => {
      this.proizvodjac = data;
    });
  }


  onSubmit(): void {
    if (this.addProizvodForm.valid) {
      const formValues = this.addProizvodForm.value;
      const newProizvod: Proizvod = {
        proizvodIme: formValues.proizvodIme,
        proizvodImageUrl: formValues.proizvodImageUrl,
        tip: formValues.tip,
        cena: formValues.cena,
        opis: formValues.opis,
        proizvodjac: {
          id: formValues.proizvodjac,
          proizvodjacIme: '',
          opis: ''
        },
        id: 0
      };
      this.proizvodService.addProizvod(newProizvod).subscribe(
        response => {
          console.log('Proizvod uspesno dodat', response);
          this.addProizvodForm.reset();
        },
        error => {
          console.error('Error adding proizvod', error);
        }
      );
    }
  }
}
