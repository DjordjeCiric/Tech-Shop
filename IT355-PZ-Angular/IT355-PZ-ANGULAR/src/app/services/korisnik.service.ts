import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from '../models/korisnik';

@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  private apiUrl = 'http://localhost:8080/korisnik'; 

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  deleteKorisnik(korisnikId: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.apiUrl}/${korisnikId}`, {headers});
  }

  updateKorisnik(korisnik: Korisnik): Observable<Korisnik> {
    const headers = this.getAuthHeaders();
    return this.http.put<Korisnik>(`${this.apiUrl}/updateKorisnik`, korisnik, {headers});
  }

  getKorisnik(): Observable<Korisnik[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<Korisnik[]>(`${this.apiUrl}/getAll`, {headers});
  }

  getKorisnikById(korisnikId: number): Observable<Korisnik> {
    const headers = this.getAuthHeaders();
    return this.http.get<Korisnik>(`${this.apiUrl}/${korisnikId}`, {headers});
  }

  getCurrentKorisnikById(): number | null {
    const user = localStorage.getItem('korisnik');
    return user ? JSON.parse(user).id : null;
  }


  login(korisnik: Korisnik): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, korisnik);
  }

  register(korisnik: Korisnik): Observable<Korisnik> {
    return this.http.post<Korisnik>(`${this.apiUrl}/addKorisnik`, korisnik);
  }

  checkEmailExists(email: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/checkEmailExists`, { params: { email } });
  }

  setToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('korisnik');
  }
}
