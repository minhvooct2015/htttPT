import { Component } from '@angular/core';
import {SanPham} from "../../components/admin/sanpham.model";
import {LoaiSanPham} from "../../components/admin/loaisanpham.model";
import {AdminService} from "../../services/admin.service";
import {OrderService} from "../../services/order.service";
import {Product, TrangThaiDonHang} from "../product.model";
import { HttpErrorResponse } from '@angular/common/http';
import {Router} from "@angular/router";
@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css']
})
export class ProductPageComponent {
  sanphamList: SanPham[] = [];
  mulens: SanPham[] = [];  // The list of filtered products based on loaiSP
  muluoitrais: SanPham[] = [];  // The list of filtered products based on loaiSP
  munapbacks: SanPham[] = [];  // The list of filtered products based on loaiSP

  newSanPham = { tenSP: '', moTa: '', giaSP: 0, loaiSP: '', soLuongTonKho: 0 };
  selectedFile: File | null = null;
  loaiSanPhamList: LoaiSanPham[] = [];
  imageRootPath: string = 'http://localhost:9002/san-pham/';


  constructor(private adminService: AdminService, private orderService: OrderService, private router: Router) {}

  ngOnInit(): void {
    this.getSanPham();
    // this.getLoaiSanPham();
  }

  addCart(sanPham: SanPham) {
    this.orderService.addToCart(sanPham);
  }
  // Function to filter sanphamList by loaiSP
  getSanPhamListByLoaiSP(loaiSP: string): SanPham[] {
    return this.sanphamList.filter(sanpham => sanpham.loaiSP === loaiSP);
  }

  // getSanPham() {
  //   this.adminService.getSanPham().subscribe((data: any) => {
  //     this.sanphamList = this.getSanPhamCoSL(data);
  //     this.mulens = this.getSanPhamListByLoaiSP("LSP051");
  //     this.muluoitrais = this.getSanPhamListByLoaiSP("LSP254");
  //     this.munapbacks = this.getSanPhamListByLoaiSP("LSP418");
  //   });
  // }

  getSanPham() {
    this.adminService.getSanPham().subscribe(
      (data: any) => {
        this.sanphamList = this.getSanPhamCoSL(data);
        this.mulens = this.getSanPhamListByLoaiSP("LSP051");
        this.muluoitrais = this.getSanPhamListByLoaiSP("LSP254");
        this.munapbacks = this.getSanPhamListByLoaiSP("LSP418");
      },
      (error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Specific handling for 401 Unauthorized
          alert('You need to log in to access this page.');
          this.router.navigate(['/login']);
        } else if (error.status === 500) {
          try {
            // Parse the backend response to extract details
            const errorDetails = error.error?.details || 'An unexpected error occurred.';
            const errorMessage = `Server Error: ${errorDetails}`;
            if( error.error.details?.includes("Unauthorized, status code 401")) this.router.navigate(['/login']);
          } catch (e) {
            alert('An unexpected server error occurred.');
          }
        } else {
          // General error handling for other status codes
          alert(`An error occurred: ${error.message}`);
        }
      }
    );
  }


  getSanPhamCoSL(response: SanPham[]): SanPham[] {
    const result: SanPham[] = [];
    for (let product of response) {
      if (product.soLuongTonKho > 0) {
        result.push(product);
      }
    }
    return result;
  }

  getLoaiSanPham() {
    this.adminService.getLoaiSanPham().subscribe((data: any) => {
      this.loaiSanPhamList = data;
    });
  }
}
