import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoaiSanPhamComponent } from './components/admin/loaisanpham/loaisanpham.component';
import { LoginComponent } from './components/login/login.component';
import {RegisterComponent} from "./components/register/register.component";
import {SanPhamComponent} from "./components/admin/sanpham/sanpham.component";
import {ProductPageComponent} from "./customer/product-page/product-page.component";


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin/loaisanpham', component: LoaiSanPhamComponent },
  { path: 'admin/sanpham', component: SanPhamComponent },
  // { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'home', component: ProductPageComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
