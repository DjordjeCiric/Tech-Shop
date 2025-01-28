import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Korisnik } from 'src/app/models/korisnik';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  korisnik: Korisnik = { id: 0, korisnikEmail: '', korisnikIme: '', korisnikSifra: '', admin: 0 };
  errorMessage: string = '';
  confirmPassword: string = '';

  constructor(private korisnikService: KorisnikService, private router: Router) {}

  onRegister(): void {
    if (this.isValidRegistration()) {
      this.korisnikService.checkEmailExists(this.korisnik.korisnikEmail).subscribe(
        emailExists => {
          if (emailExists) {
            this.errorMessage = 'Email is already in use';
          } else {
            this.korisnikService.register(this.korisnik).subscribe(
              response => {
                this.router.navigate(['/login']);
              },
              error => {
                this.errorMessage = 'Registration failed. Please try again.';
              }
            );
          }
        }
      );
    } else {
      this.errorMessage = 'Invalid registration data';
    }
  }

  private isValidRegistration(): boolean {
    return (
      this.korisnik.korisnikEmail.trim() !== '' &&
      this.korisnik.korisnikIme.trim() !== '' &&
      this.korisnik.korisnikSifra.trim() !== '' &&
      this.korisnik.korisnikSifra === this.confirmPassword
    );
  }
}
