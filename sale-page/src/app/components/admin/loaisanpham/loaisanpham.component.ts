import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import {LoaiSanPham} from "../loaisanpham.model";

@Component({
  selector: 'app-loaisanpham',
  templateUrl: './loaisanpham.component.html',
  styleUrls: ['./loaisanpham.component.css']
})
export class LoaiSanPhamComponent implements OnInit {
  loaisanpham: LoaiSanPham[] = [];
  newLoai: LoaiSanPham = {maDanhMuc: '', tenDanhMuc: '', moTa: '' };
  isEditing: boolean = false;
  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.getLoaiSanPham();
  }

  getLoaiSanPham() {
    this.adminService.getLoaiSanPham().subscribe((data: any) => {
      this.loaisanpham = data;
    });
  }

  addLoaiSanPham() {
    this.adminService.addLoaiSanPham(this.newLoai).subscribe(() => {
      this.getLoaiSanPham();
      this.isEditing = false;
    });
  }

  deleteLoaiSanPham(id: string) {
    this.adminService.deleteLoaiSanPham(id).subscribe(() => {
      this.getLoaiSanPham();
    });
  }

  editLoaiSanPham(loai: any): void {
    this.isEditing = true;
    this.newLoai = { ...loai }; // Copy the selected loai to the form
  }

  updateLoaiSanPham(): void {
    this.adminService.updateLoaiSanPham(this.newLoai).subscribe(response => {
      this.getLoaiSanPham();
      this.newLoai = { maDanhMuc:'', tenDanhMuc: '', moTa: '' };
      this.isEditing = false; // Exit edit mode
    });
  }
}
