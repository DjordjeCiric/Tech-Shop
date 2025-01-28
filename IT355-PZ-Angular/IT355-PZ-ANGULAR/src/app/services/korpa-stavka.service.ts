import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { KorisnikService } from './korisnik.service';
import { KorpaStavka } from '../models/korpa-stavka';

@Injectable({
  providedIn: 'root'
})
export class KorpaStavkaService {
  private apiUrl = 'http://localhost:8080/korpaStavka';

  constructor(private http: HttpClient, private korisnikService: KorisnikService) {}

  private getAuthHeaders(): HttpHeaders {
    const token = this.korisnikService.getToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }
  addKorpaStavka(korpaStavka: KorpaStavka): Observable<KorpaStavka> {
    const headers = this.getAuthHeaders();
    return this.http.post<KorpaStavka>(`${this.apiUrl}/addKorpaStavka`, korpaStavka, {
      headers,
    });
  }

  findKorpaStavkaByKorisnikId(korisnikId: number): Observable<KorpaStavka[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<KorpaStavka[]>(
      `${this.apiUrl}/findByKorpa_Korisnik_Id/${korisnikId}`,
      { headers }
    );
  }

  deleteKorpaStavka(korpaStavkaId: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.apiUrl}/${korpaStavkaId}`, {headers});
  }

  deleteSveKorpaStavke(korisnikId: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.apiUrl}/deleteByKorpa_Korisnik_Id/${korisnikId}`, { headers });
  }

}
