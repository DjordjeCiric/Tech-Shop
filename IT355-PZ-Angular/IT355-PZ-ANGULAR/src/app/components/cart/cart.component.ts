import { Component, OnInit } from '@angular/core';
import { KorpaStavka } from 'src/app/models/korpa-stavka';
import { KorpaStavkaService } from 'src/app/services/korpa-stavka.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{
  korpaStavka: KorpaStavka[] = [];
  korisnikId: number = 0;
  totalCost: number = 0;
  constructor(private korpaStavkaService: KorpaStavkaService) {}

  ngOnInit(): void {
    this.korisnikId = JSON.parse(localStorage.getItem('korisnik')!).id;
    const korisnikId = this.korisnikId;
    this.korpaStavkaService.findKorpaStavkaByKorisnikId(korisnikId).subscribe({
      next: (items) => this.korpaStavka = items,
      error: (err) => console.error(err)
    });
  }

  deleteKorpaStavka(korpaStavkaId: number): void {
    this.korpaStavkaService.deleteKorpaStavka(korpaStavkaId).subscribe({
      next: () => {
        this.korpaStavka = this.korpaStavka.filter(item => item.id !== korpaStavkaId);
      },
      error: (err) => console.error(err)
    });
  }

  deleteSveKorpaStavke(): void {
    this.korisnikId = JSON.parse(localStorage.getItem('korisnik')!).id;
    const korisnikId = this.korisnikId;
    this.korpaStavkaService.deleteSveKorpaStavke(korisnikId).subscribe({
      next: () => {
        this.korpaStavka = [];
      },
      error: (err) => console.error(err)
    });
  }
  calculateTotalCost(): void {
    this.totalCost = this.korpaStavka.reduce((acc, item) => acc + (item.proizvod.cena * item.kolicina), 0);
    alert(`Total cost: ${this.totalCost} $`);
    this.deleteSveKorpaStavke();
  }
}
