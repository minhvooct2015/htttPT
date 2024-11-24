import { Component } from '@angular/core';
import {SanPham} from "../../components/admin/sanpham.model";
import {LoaiSanPham} from "../../components/admin/loaisanpham.model";
import {AdminService} from "../../services/admin.service";

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


  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getSanPham();
    this.getLoaiSanPham();
  }

  // Function to filter sanphamList by loaiSP
  getSanPhamListByLoaiSP(loaiSP: string): SanPham[] {
    return this.sanphamList.filter(sanpham => sanpham.loaiSP === loaiSP);
  }

  getSanPham() {
    this.adminService.getSanPham().subscribe((data: any) => {
      this.sanphamList = data;
      this.mulens = this.getSanPhamListByLoaiSP("LSP051");
      this.muluoitrais = this.getSanPhamListByLoaiSP("LSP254");
      this.munapbacks = this.getSanPhamListByLoaiSP("LSP418");
    });
  }

  getLoaiSanPham() {
    this.adminService.getLoaiSanPham().subscribe((data: any) => {
      this.loaiSanPhamList = data;
    });
  }
}
