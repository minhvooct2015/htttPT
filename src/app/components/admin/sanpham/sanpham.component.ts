import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import { LoaiSanPham } from '../loaisanpham.model';
import {SanPham} from "../sanpham.model";


@Component({
  selector: 'app-sanpham',
  templateUrl: './sanpham.component.html',
  styleUrls: ['./sanpham.component.css']
})
export class SanPhamComponent implements OnInit {
  sanphamList: SanPham[] = [];
  newSanPham = { tenSP: '', moTa: '', giaSP: 0, loaiSP: '', soLuongTonKho: 0 };
  selectedFile: File | null = null;
  loaiSanPhamList: LoaiSanPham[] = [];
  // imageRootPath: string = 'http://localhost:9002/san-pham/';
  imageRootPath ='http://192.168.1.111:9002/san-pham/';


  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getSanPham();
    this.getLoaiSanPham();
  }

  getSanPham() {
    this.adminService.getSanPham().subscribe((data: any) => {
      this.sanphamList = data;
    });
  }
  getLoaiSanPham() {
    this.adminService.getLoaiSanPham().subscribe((data: any) => {
      this.loaiSanPhamList = data;
    });
  }
  addSanPham() {
    const formData = new FormData();
    let sanphamadd = JSON.stringify(this.newSanPham);
    console.log("ap add", sanphamadd)
    formData.append('sanPhamDTO', sanphamadd);
    if (this.selectedFile) {
      formData.append('file', this.selectedFile, this.selectedFile.name);
    }
let loaisp = this.newSanPham.loaiSP
    this.adminService.addSanPham(formData, loaisp).subscribe(() => {
      this.getSanPham();
      this.newSanPham = { tenSP: '', moTa: '', giaSP: 0, loaiSP: '', soLuongTonKho: 0 };
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  deleteSanPham(id: string) {
    this.adminService.deleteSanPham(id).subscribe(() => {
      this.getSanPham();
    });
  }

  editSanPham(sanpham: any) {
    // Logic for editing a product
  }
}
