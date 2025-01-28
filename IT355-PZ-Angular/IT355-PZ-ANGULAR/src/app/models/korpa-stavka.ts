import { Korpa } from './korpa';
import { Proizvod } from './proizvod';
export interface KorpaStavka {
    id: number;
    korpa: Korpa;
    proizvod: Proizvod;
    kolicina: number;
}