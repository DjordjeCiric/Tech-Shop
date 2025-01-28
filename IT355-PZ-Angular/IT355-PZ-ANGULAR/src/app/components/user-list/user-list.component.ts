import { Component } from '@angular/core';
import { Korisnik } from 'src/app/models/korisnik';
import { KorisnikService } from 'src/app/services/korisnik.service';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  korisnik: Korisnik[] = [];
  selectedKorisnik: Korisnik | null = null;

  constructor(private korisnikService: KorisnikService) { }
  
  ngOnInit(): void {
    this.korisnikService.getKorisnik().subscribe(data => {
      this.korisnik = data;
    });
  }
  deleteKorisnik(korisnikId: number): void {
    if (confirm("Jeste li sigurni da zelite da izbrisete ovog korisnika?")) {
      this.korisnikService.deleteKorisnik(korisnikId).subscribe(() => {
        this.korisnik = this.korisnik.filter(korisnik => korisnik.id !== korisnikId);
      });
    }
  }
  updateKorisnik(korisnikId: number): void {
    this.selectedKorisnik = this.korisnik.find((korisnik: Korisnik) => korisnik.id === korisnikId) || null;
  }
  cancelUpdate(): void {
    this.selectedKorisnik = null;
  }

  submitUpdate(): void {
    if (this.selectedKorisnik) {
      this.korisnikService.updateKorisnik(this.selectedKorisnik).subscribe(updateKorisnik => {
        const index = this.korisnik.findIndex(korisnik => korisnik.id === updateKorisnik.id);
        if (index !== -1) {
          this.korisnik[index] = updateKorisnik;
        }
        this.selectedKorisnik = null;
      });
    }
  }
}
