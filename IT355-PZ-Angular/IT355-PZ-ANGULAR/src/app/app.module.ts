import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { CartComponent } from './components/cart/cart.component';
import { ProizvodDetailsComponent } from './components/proizvod-details/proizvod-details.component';
import { AddProizvodjacComponent } from './components/add-proizvodjac/add-proizvodjac.component';
import { AddProizvodComponent } from './components/add-proizvod/add-proizvod.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomePageComponent,
    NavbarComponent,
    UserProfileComponent,
    UserListComponent,
    CartComponent,
    ProizvodDetailsComponent,
    AddProizvodjacComponent,
    AddProizvodComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
