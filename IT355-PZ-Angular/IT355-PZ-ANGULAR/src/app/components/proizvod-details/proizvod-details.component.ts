import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Proizvod } from 'src/app/models/proizvod';
import { Proizvodjac } from 'src/app/models/proizvodjac';
import { ProizvodService } from 'src/app/services/proizvod.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { RecenzijaService } from 'src/app/services/recenzija.service';
import { Recenzija } from 'src/app/models/recenzija';
import { Korisnik } from 'src/app/models/korisnik';
import { KorpaStavkaService } from 'src/app/services/korpa-stavka.service';
import { Korpa } from 'src/app/models/korpa';
import { KorpaStavka } from 'src/app/models/korpa-stavka';
import { KorpaService } from 'src/app/services/korpa.service';

@Component({
  selector: 'app-proizvod-details',
  templateUrl: './proizvod-details.component.html',
  styleUrls: ['./proizvod-details.component.css']
})
export class ProizvodDetailsComponent implements OnInit{
  proizvod!: Proizvod;
  proizvodjac: Proizvodjac[] = [];
  isEditing = false;
  editForm!: Proizvod;
  recenzija: Recenzija[] = [];
  newRecenzija: Recenzija = {
    id: 0,
    korisnik: {} as Korisnik,
    proizvod: {} as Proizvod,
    recenzija: '',
  };
  hasReviewed = false;

  isLoggedIn = false;
  email: string | null = null;
  username: string | null = null;
  isAdmin = false;
  korisnik!: Korisnik;
  quantity: number = 1;
  korisnikId: number = 0;

  korpa!: Korpa;
  korpaId: number | undefined;

  constructor(
    private proizvodService: ProizvodService,
    private korisnikService: KorisnikService,
    private recenzijaService: RecenzijaService,
    private korpaStavkaService: KorpaStavkaService,
    private korpaService: KorpaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  getProizvod(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.proizvodService.getProizvodById(id).subscribe((proizvod) => (this.proizvod = proizvod));
    }
  }

  ngOnInit(): void {
    this.korisnikId = JSON.parse(localStorage.getItem('korisnik')!).id;

    this.getProizvod();

    this.isLoggedIn = this.korisnikService.isLoggedIn();
    if (this.isLoggedIn) {
      const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
      this.email = korisnik.korisnikEmail;
      this.username = korisnik.korisnikIme;
      this.isAdmin = korisnik.admin === 1;
      this.newRecenzija.korisnik = korisnik;
    }

    const proizvodId = this.route.snapshot.paramMap.get('id')!;
    this.proizvodService.getProizvodById(proizvodId).subscribe((proizvod) => {
      this.proizvod = proizvod;
      this.editForm = { ...proizvod };
      this.newRecenzija.proizvod = proizvod;
      this.fetchReviews();
    });

    this.proizvodService
      .getProizvodjac()
      .subscribe((proizvodjac) => (this.proizvodjac = proizvodjac));

    this.fetchCartId();
  }

  fetchCartId(): void {
    this.korpaService.getByKorisnikId(this.korisnikId).subscribe(
      (korpa) => {
        this.korpaId = korpa.id;
        console.log('Cart ID:', this.korpaId);
      },
      (error) => {
        console.error('Error fetching cart:', error);
      }
    );
  }

  fetchReviews(): void {
    if (this.proizvod) {
      this.recenzijaService.getRecenzijaByProizvodId(this.proizvod.id).subscribe(
        (recenzija) => {
          this.recenzija = recenzija;
          const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
          this.hasReviewed = this.recenzija.some(
            (review) => review.korisnik.id === korisnik.id
          );
        },
        (error) => {
          console.error('Error fetching reviews:', error);
        }
      );
    }
  }

  editProizvod(): void {
    this.isEditing = true;
  }

  deleteProizvod(id: number): void {
    this.proizvodService.deleteProizvod(id).subscribe(() => {
      this.router.navigate(['/homePage']);
    });
  }

  onSubmit(): void {
    this.proizvodService.updateProizvod(this.editForm).subscribe(() => {
      this.proizvod = { ...this.editForm };
      this.isEditing = false;
    });
  }

  deleteRecenzija(recenzijaId: number): void {
    this.recenzijaService.deleteRecenziju(recenzijaId).subscribe(
      () => {
        console.log('Review deleted successfully');
        this.recenzija = this.recenzija.filter((recenzija) => recenzija.id !== recenzijaId);
        const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
        this.hasReviewed = this.recenzija.some(
          (review) => review.korisnik.id === korisnik.id
        );
      },
      (error) => {
        console.error('Error deleting review:', error);
      }
    );
  }

  canDeleteRecenziju(review: Recenzija): boolean {
    if (this.isAdmin) return true;
    const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
    return review.korisnik.id === korisnik.id;
  }

  addRecenziju(): void {
    this.recenzijaService.createRecenziju(this.newRecenzija).subscribe(
      (recenzija) => {
        this.recenzija.push(recenzija);
        this.newRecenzija.recenzija = '';
        this.hasReviewed = true;
      },
      (error) => {
        console.error('Error adding review:', error);
      }
    );
  }

  addToCart(): void {
    const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
    const proizvodId = this.route.snapshot.paramMap.get('id')!;
    const parsedProizvodId: number = parseInt(proizvodId, 10);

    const korisnikDetail: Korisnik = {
      id: korisnik.id,
      korisnikIme: korisnik.korisnikIme,
      korisnikSifra: korisnik.korisnikSifra,
      korisnikEmail: korisnik.korisnikEmail,
      admin: korisnik.admin,
    };

    const korpa: Korpa = {
      id: this.korpaId!,
      korisnik: korisnikDetail,
    };

    const korpaStavka: KorpaStavka = {
      id: 0,
      korpa: korpa,
      proizvod: {
        id: parsedProizvodId,
        proizvodIme: this.proizvod.proizvodIme,
        proizvodImageUrl: this.proizvod.proizvodImageUrl,
        tip: this.proizvod.tip,
        cena: this.proizvod.cena,
        opis: this.proizvod.opis,
        proizvodjac: this.proizvod.proizvodjac,
      },
      kolicina: this.quantity,
    };

    this.korpaStavkaService.addKorpaStavka(korpaStavka).subscribe(
      (response) => {
        console.log('Proizvod dodat u korpu:', response);
        alert('Proizvod je uspesno dodat');
      },
      (error) => {
        console.error('Error adding item to cart:', error);
      }
    );
  }
}
