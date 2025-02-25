import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SanPham} from "../components/admin/sanpham.model";
import {Observable} from "rxjs";
import {DonHang, Product} from "../customer/product.model";
import {API_BASE_URL, ORDER_SERVICE_URL} from "../constants";

let apiUrl = ORDER_SERVICE_URL;

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = apiUrl + '/donhang';
  private apiUrlCTDH = 'http://localhost:9003/chitietdonhang';
  constructor(private http: HttpClient) {}


  getProductsByUser(userId: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${API_BASE_URL  + "/donhang/dssp"}/${userId}`);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl + "/all-dssp"}`);
  }

  removeSanPhamInCart(spId: string): Observable<void> {
    console.log('Removing product with ID:', spId);
    return this.http.delete<void>(`${this.apiUrlCTDH}/${spId}`);
  }

  // Method to initiate the checkout process
  checkout(maDH: string, donHang: DonHang): Observable<void> {
    console.log("checkout in order Service")

    return this.http.put<void>(`${this.apiUrl + "/checkout"}/${maDH}`, donHang);

  }

  addToCart(sanpham: SanPham): void {
    const donHang = {
      maNguoiDung: localStorage.getItem("userNumber"), // Replace with actual user ID
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
        alert('Đã thêm giỏ hàng thành công!');
      },
      (error) => {
        console.error('Lỗi thêm đơn hàng', error);
        alert('Lỗi thêm đơn hàng!');
      }
    );
  }
}
