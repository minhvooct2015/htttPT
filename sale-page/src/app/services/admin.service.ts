import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoaiSanPham} from "../components/admin/loaisanpham.model";
import {SanPham, UserInfor} from "../components/admin/sanpham.model";
import {jwtDecode} from "jwt-decode";
import {API_BASE_URL, PRODUCT_SERVICE_URL} from "../constants";
@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = API_BASE_URL; // Replace with your API base URL

  constructor(private http: HttpClient) {}

  // Loai San Pham APIs
  getLoaiSanPham(): Observable<LoaiSanPham[]> {
    return this.http.get<LoaiSanPham[]>(`${PRODUCT_SERVICE_URL}/loai-san-pham/all`);
  }

  addLoaiSanPham(data: any): Observable<any> {
    return this.http.post(`${PRODUCT_SERVICE_URL}/loai-san-pham`, data);
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

  onLogout(): void {
    // Clear user-related data from localStorage
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    localStorage.removeItem('userNumber');
    localStorage.removeItem('tentk');

  }
  getUserInfor(token: any): UserInfor {
    // try {
      const decodedToken: any = jwtDecode(token);
      let tentk = decodedToken.upn;  // Username (user principal name)
      let userNumber = decodedToken.userNumber;  // User account (userNumber)
      let userName = decodedToken.tenUser

      const userInfor: UserInfor = {
        userNumber: userNumber, // User account (userNumber)
        userName: userName,
        tenTK: tentk,// Full user name
        role: decodedToken.groups ? decodedToken.groups[0] : '' // First group in array, if any
      };

      return userInfor;
    // } catch (error) {
    //   console.error('Failed to decode token:', error);
    //   return
    // }
  }

  isLoginedUser(): boolean {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        return false;
      }
      const decoded: any = jwtDecode(token); // Use 'any' type if the structure is unknown
      const now = Math.floor(Date.now() / 1000); // Current time in seconds
      return decoded.exp > now; // Check if the token is still valid
    } catch (error) {
      console.error('Invalid token:', error);
      return false;
    }
  }




}
