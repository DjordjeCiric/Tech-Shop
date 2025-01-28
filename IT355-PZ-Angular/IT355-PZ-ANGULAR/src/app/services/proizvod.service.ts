import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proizvod } from '../models/proizvod';
import { Proizvodjac } from '../models/proizvodjac';
import { KorisnikService } from './korisnik.service';
@Injectable({
  providedIn: 'root'
})
export class ProizvodService {
  private baseUrl = 'http://localhost:8080';
 
 
  constructor(private http: HttpClient, private korisnikService: KorisnikService) {}
 
  private getAuthHeaders(): HttpHeaders {
    const token = this.korisnikService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  getProizvod(): Observable<Proizvod[]> {
    return this.http.get<Proizvod[]>(`${this.baseUrl}/proizvod/getAll`);
  }
  
  getProizvodjac(): Observable<Proizvodjac[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<Proizvodjac[]>(`${this.baseUrl}/proizvodjac/getAll`, {headers});
  }

  addProizvodjac(proizvodjac: Proizvodjac): Observable<Proizvodjac> {
    const headers = this.getAuthHeaders();
    return this.http.post<Proizvodjac>(`${this.baseUrl}/proizvodjac/addProizvodjac`, proizvodjac, {headers});
  }

  addProizvod(proizvod: Proizvod): Observable<Proizvod> {
    const headers = this.getAuthHeaders();
    return this.http.post<Proizvod>(`${this.baseUrl}/proizvod/addProizvod`, proizvod, {headers});
  }

  getProizvodById(prozivodId: string): Observable<Proizvod> {
    const headers = this.getAuthHeaders();
    return this.http.get<Proizvod>(`${this.baseUrl}/proizvod/${prozivodId}`, {headers});
  }

  updateProizvod(proizvod: Proizvod): Observable<Proizvod> {
    const headers = this.getAuthHeaders();
    return this.http.put<Proizvod>(`${this.baseUrl}/proizvod/updateProizvod`, proizvod, {headers});
  }

  deleteProizvod(proizvodId: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.baseUrl}/proizvod/${proizvodId}`, {headers});
  }
}
