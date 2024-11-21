import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoaiSanPham} from "../components/admin/loaisanpham.model";
import {SanPham} from "../components/admin/sanpham.model";

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = 'http://localhost:9002'; // Replace with your API base URL

  constructor(private http: HttpClient) {}

  // Loai San Pham APIs
  getLoaiSanPham(): Observable<LoaiSanPham[]> {
    return this.http.get<LoaiSanPham[]>(`${this.baseUrl}/loai-san-pham/all`);
  }

  addLoaiSanPham(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/loai-san-pham`, data);
  }

  updateLoaiSanPham(data: LoaiSanPham): Observable<any> {
    return this.http.put(`${this.baseUrl}/loai-san-pham/${data.maDanhMuc}`, data);
  }

  deleteLoaiSanPham(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/loai-san-pham/${id}`);
  }

  // San Pham APIs
  getSanPham(): Observable<SanPham[]> {
    return this.http.get<SanPham[]>(`${this.baseUrl}/san-pham/all`);
  }

  addSanPham(formData: FormData, loaisp: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/san-pham?loaiSanPhamId=${loaisp}`, formData);
  }

  deleteSanPham(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/san-pham/${id}`);
  }

  //todo hide button if khong xoa duoc loai s do bi khoa ngoai
  //todo hide button if khong xoa duoc loai s do bi khoa ngoai
}
