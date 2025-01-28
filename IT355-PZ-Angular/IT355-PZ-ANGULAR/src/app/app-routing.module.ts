import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { CartComponent } from './components/cart/cart.component';
import { ProizvodDetailsComponent } from './components/proizvod-details/proizvod-details.component';
import { AddProizvodjacComponent } from './components/add-proizvodjac/add-proizvodjac.component';
import { AddProizvodComponent } from './components/add-proizvod/add-proizvod.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'homePage', component: HomePageComponent},

  {path: 'register', component: RegisterComponent},
  {path: 'userProfile', component: UserProfileComponent},
  {path: 'userList', component: UserListComponent},
  {path: 'cart', component: CartComponent },
  {path: 'proizvod/:id', component: ProizvodDetailsComponent },
  {path: 'addProizvodjac', component: AddProizvodjacComponent},
  {path: 'addProizvod', component: AddProizvodComponent},

  { path: '', redirectTo: '/homePage', pathMatch: 'full' },
  { path: '**', redirectTo: '/homePage' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
