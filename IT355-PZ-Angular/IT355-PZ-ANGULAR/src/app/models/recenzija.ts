import { Korisnik } from './korisnik';
import { Proizvod } from './proizvod';

export interface Recenzija {
  id: number;
  korisnik: Korisnik;
  proizvod: Proizvod;
  recenzija: string;
}