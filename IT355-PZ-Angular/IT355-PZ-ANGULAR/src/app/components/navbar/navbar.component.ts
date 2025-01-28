import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  email: string | null = null;
  username: string | null = null;
  isAdmin = false;
  

  constructor(private korisnikService: KorisnikService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.korisnikService.isLoggedIn();
    if (this.isLoggedIn) {
      const korisnik = JSON.parse(localStorage.getItem('korisnik')!);
      this.email = korisnik.korisnikEmail;
      this.username = korisnik.korisnikIme;
      this.isAdmin = korisnik.admin === 1;
    }
  }
  

  logout(): void {
    this.korisnikService.logout();
    this.router.navigate(['/homePage']);
  }
}
