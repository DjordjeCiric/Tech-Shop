import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/models/korisnik';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  korisnik: Korisnik | null = null;
  newPassword: string = '';
  confirmPassword: string = '';
  errorMessage: string = '';
  showPasswordFields: boolean = false;

  constructor(private korisnikService: KorisnikService, private router: Router) {}

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    const korisnikId = this.korisnikService.getCurrentKorisnikById();
    if (korisnikId) {
      this.korisnikService.getKorisnikById(korisnikId).subscribe(
        (data) => {
          this.korisnik = data;
        },
        (error) => {
          console.error(error);
        }
      );
    } else {
      this.router.navigate(['/login']);
    }
  }

  togglePasswordFields(): void {
    this.showPasswordFields = !this.showPasswordFields;
  }

  onSubmit(): void {
    if (this.newPassword !== this.confirmPassword) {
      alert('Passwords must match');
      return;
    }

    if (this.korisnik) {
      this.korisnik.korisnikSifra = this.newPassword;
      this.korisnikService.updateKorisnik(this.korisnik).subscribe(
        (data) => {
          alert('Password changed');
          this.router.navigate(['/homePage']);
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }
}
