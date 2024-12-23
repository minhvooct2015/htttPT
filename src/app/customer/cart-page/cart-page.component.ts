import { Component } from '@angular/core';
import {Product, TrangThaiDonHang} from "../product.model";
import {OrderService} from "../../services/order.service";


@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent {
  productsByUser: Product[] = [];
  productsProcessingByUser: Product[] = [];
  productsProcessedByUser: Product[] = [];
  productsHuyByUser: Product[] = [];
  phiVC: number = 0;
  maDH: string = ""
  userId = localStorage.getItem("userNumber"); // Example userId, can be dynamic
  // imageRootPath: string = 'http://localhost:9002/san-pham/';
  imageRootPath ='http://192.168.1.111:9002/san-pham/';
  totalSum = 0;
  finalTotal = 0

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadProductsByUser();
  }

  // // Function to get only products with TrangThaiDonHang.DANG_DAT
  // getPendingOrders() {
  //   return this.productsByUser.filter(product => product.trangThaiDonHang === TrangThaiDonHang.DANG_DAT);
  // }

  loadProductsByUser(): void {
    //Only get san pham suer dang chon
    this.orderService.getProductsByUser(this.userId? this.userId : "").subscribe((response: Product[]) => {
      console.log('Initial response:', response);

// Filter each array based on TrangThaiDonHang
      this.productsByUser = this.extractProductsWithTrangThai(response, TrangThaiDonHang.DANG_DAT)
      this.productsProcessingByUser = this.extractProductsWithTrangThai(response, TrangThaiDonHang.DANG_XU_LY)
      this.productsProcessedByUser = this.extractProductsWithTrangThai(response, TrangThaiDonHang.DA_GIAO)
      this.productsHuyByUser = this.extractProductsWithTrangThai(response, TrangThaiDonHang.HUY)

      console.log('Products by user:', this.productsByUser);
      console.log('Processing by user:', this.productsProcessingByUser);
      console.log('Processed by user:', this.productsProcessedByUser);
      console.log('Huy by user:', this.productsHuyByUser);
      this.phiVC = this.productsByUser[0]?.phiGiaoHang
      this.maDH = this.productsByUser[0]?.chiTietDonHangDTO?.maDh
      this.totalSum = this.calculateTotalPrice(this.productsByUser);
    });
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

  // Calculate the total price for all products
  calculateTotalPrice(products: any[]): number {
    return products.reduce((total, product) => {
      const productTotal = product.giaSP * product.chiTietDonHangDTO.soLuong;
      return total + productTotal;
    }, 0);
  }

  // Handle quantity decrease
  decreaseQuantity(product: any) {
    if (product.chiTietDonHangDTO.soLuong > 1) {
      product.chiTietDonHangDTO.soLuong--;
    }
  }

  // Method to call API when "Proceed Checkout" button is clicked
  proceedCheckout(tongtien: number) {
    if (confirm(`Are you sure you want to checkout?`)) {

      const donhang = {trangThai: TrangThaiDonHang.DANG_XU_LY,
      tongTien: tongtien};
      console.log("checkout")
      this.orderService.checkout(this.maDH, donhang).subscribe(
        (response) => {
          console.log('Checkout successful!', response);
          alert("successful")
          // Handle success (e.g., navigate to a success page, display a message, etc.)
        },
        (error) => {
          console.error('Checkout failed!', error);
          // Handle error (e.g., display an error message)
        });
      this.loadProductsByUser();
    }
  }

  // Remove product method
  removeProduct(product: Product) {
    if (confirm(`Are you sure you want to remove ${product.tenSP}?`)) {
      this.orderService.removeSanPhamInCart(product.chiTietDonHangDTO?.maCtdh).subscribe({
        next: () => {
          console.log('Product removed from cart');
          this.loadProductsByUser(); // Refresh the product list after removal
        },
        error: (err) => {
          console.error('Error while removing product:', err);
          alert('Failed to remove the product. Please try again.');
        }
      });
    }
  }


  // Handle quantity increase
  increaseQuantity(product: any) {
    if (product.chiTietDonHangDTO.soLuong < product.soLuongTonKho) {
      product.chiTietDonHangDTO.soLuong++;
    }
  }

  // Handle changes in quantity input
  onQuantityChange(product: any) {
    // You can add logic here to update other parts of the app or check for valid range.
    if (product.chiTietDonHangDTO.soLuong > product.soLuongTonKho) {
      product.chiTietDonHangDTO.soLuong = product.soLuongTonKho;
    }
  }
}
