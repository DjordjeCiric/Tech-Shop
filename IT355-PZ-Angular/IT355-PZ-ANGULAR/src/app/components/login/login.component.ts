import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Korisnik } from 'src/app/models/korisnik';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  korisnik: Korisnik = { id: 0, korisnikEmail: '', korisnikIme: '', korisnikSifra: '', admin: 0 };
  errorMessage: string = '';

  constructor(private korisnikService: KorisnikService, private router: Router) {}

  onLogin(): void {
    this.korisnikService.login(this.korisnik).subscribe(
      response => {
        this.korisnikService.setToken(response.jwt);
        localStorage.setItem('korisnik', JSON.stringify(response.korisnik));
        this.router.navigate(['/homePage']);
      },
      error => {
        this.errorMessage = 'Invalid email or password';
      }
    );
  }
}
