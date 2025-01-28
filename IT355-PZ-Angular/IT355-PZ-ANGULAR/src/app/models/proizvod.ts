import { Proizvodjac } from "./proizvodjac";

export interface Proizvod {
  id: number;
  proizvodIme: string;
  proizvodImageUrl : string;
  cena: number;
  opis: string;
  tip: string;
  proizvodjac: Proizvodjac;
}