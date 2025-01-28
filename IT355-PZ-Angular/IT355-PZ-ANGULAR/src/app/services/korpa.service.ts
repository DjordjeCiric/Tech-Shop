import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Korpa } from '../models/korpa';
import { KorpaStavka } from '../models/korpa-stavka';
import { KorisnikService } from './korisnik.service';
@Injectable({
  providedIn: 'root'
})
export class KorpaService {

  private baseUrl = 'http://localhost:8080/korpa';

  constructor(private http: HttpClient, private korisnikService: KorisnikService) {}

  private getAuthHeaders(): HttpHeaders {
    const token = this.korisnikService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

 
  getByKorisnikId(korisnikId: number): Observable<Korpa> {
    const headers = this.getAuthHeaders();
    return this.http.get<Korpa>(`${this.baseUrl}/getByKorisnikId/${korisnikId}`, { headers });
  }
}
