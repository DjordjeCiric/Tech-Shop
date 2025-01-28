import { Component, OnInit } from '@angular/core';
import { Proizvod } from 'src/app/models/proizvod';
import { ProizvodService } from 'src/app/services/proizvod.service';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{
  proizvods: Proizvod[] = [];
  isLoggedIn = false;
  constructor(private proizvodService: ProizvodService, private korisnikService: KorisnikService) { }
  
  ngOnInit(): void {
    this.proizvodService.getProizvod().subscribe(data => {
      this.proizvods = data;
    });
    this.isLoggedIn = this.korisnikService.isLoggedIn();
  }
}
