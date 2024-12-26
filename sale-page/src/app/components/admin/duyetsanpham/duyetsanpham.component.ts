import {Component} from '@angular/core';
import {LoaiSanPham} from "../loaisanpham.model";
import {AdminService} from "../../../services/admin.service";
import {Product, TrangThaiDonHang} from "../../../customer/product.model";
import {OrderService} from "../../../services/order.service";

@Component({
  selector: 'app-duyetsanpham',
  templateUrl: './duyetsanpham.component.html',
  styleUrls: ['./duyetsanpham.component.css']
})
export class DuyetsanphamComponent {
  loaisanpham: LoaiSanPham[] = [];
  newLoai: LoaiSanPham = {maDanhMuc: '', tenDanhMuc: '', moTa: '' };
  isEditing: boolean = false;
  products: Product[] = [];
  productsDangXuLy: Product[] = [];
  productsDaGiao: Product[] = [];
  constructor(private adminService: AdminService, private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  // Method to call API when "Proceed Checkout" button is clicked
  proceedCheckout(product: Product) {
    if (confirm(`Are you sure you want to checkout?`)) {

      const donhang = {trangThai: TrangThaiDonHang.DA_GIAO};
      console.log("checkout")
      this.orderService.checkout(product.chiTietDonHangDTO.maDh, donhang).subscribe(
        (response) => {
          console.log('Checkout successful!', response);
          alert('Checkout successful!');
          this.loadProducts();
        },
        (error) => {
          console.error('Checkout failed!', error);
        });
    }
  }

  extractProductsWithTrangThai(response: Product[], ttdh: TrangThaiDonHang): Product[] {
    const result: Product[] = [];
    for (let product of response) {
      if (product.trangThaiDonHang === ttdh) {
        result.push(product);
      }
    }
    return result;
  }


  loadProducts(): void {
    //Only get san pham suer dang chon
    this.orderService.getAllProducts().subscribe((response: Product[]) => {
      console.log('Initial response:', response);

// Filter each array based on TrangThaiDonHang
      this.products = response
      this.productsDangXuLy = this.extractProductsWithTrangThai(response, TrangThaiDonHang.DANG_XU_LY);
      this.productsDaGiao = this.extractProductsWithTrangThai(response, TrangThaiDonHang.DA_GIAO);

    });
  }






}
