import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SanPham} from "../components/admin/sanpham.model";
import {Observable} from "rxjs";
import {Product} from "../customer/product.model";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9003/donhang';
  constructor(private http: HttpClient) {}


  getProductsByUser(userId: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl + "/dssp"}/${userId}`);
  }

  addToCart(sanpham: SanPham): void {
    const donHang = {
      maNguoiDung: 'U123', // Replace with actual user ID
      ngayDatHang: new Date().toISOString().split('T')[0], // Current date
      tongTien: sanpham.giaSP,
      trangThai: 'DANG_DAT',
      phuongThucGiaoHang: 'NHANH',
      phiGiaoHang: 10000,
      thoiGianDuKien: new Date().toISOString().split('T')[0],
      phuongThucThanhToan: 'THE',
      ngayThanhToan: new Date().toISOString().split('T')[0],
      dsCTDH: [
        {
          maSp: sanpham.maSP,
          soLuong: 1,
          thanhTien: sanpham.giaSP
        }
      ]
    };

    this.http.post(this.apiUrl, donHang).subscribe(
      (response) => {
        console.log('Order placed successfully', response);
        alert('Product added to cart successfully!');
      },
      (error) => {
        console.error('Error placing order', error);
        alert('Failed to add product to cart!');
      }
    );
  }
}
