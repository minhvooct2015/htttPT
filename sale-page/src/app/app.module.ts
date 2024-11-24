import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LoaiSanPhamComponent } from './components/admin/loaisanpham/loaisanpham.component';
import { SanPhamComponent } from './components/admin/sanpham/sanpham.component';
import { NavbarComponent } from './components/admin/sidebar/navbar.component';
import {TopBarComponent} from "./components/admin/topbar/topbar.component";
import { ProductPageComponent } from './customer/product-page/product-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    LoaiSanPhamComponent,
    SanPhamComponent,
    NavbarComponent,
    TopBarComponent,
    ProductPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    FormsModule
  ],
  providers: [],
  exports: [
    TopBarComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
