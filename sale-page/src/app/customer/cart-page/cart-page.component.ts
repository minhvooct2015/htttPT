import { Component } from '@angular/core';
import {Product} from "../product.model";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.css']
})
export class CartPageComponent {
  productsByUser: Product[] = [];
  userId = 'U123'; // Example userId, can be dynamic
  imageRootPath: string = 'http://localhost:9002/san-pham/';
totalSum = 0;
  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadProductsByUser();
  }

  loadProductsByUser(): void {
    this.orderService.getProductsByUser(this.userId).subscribe((response: Product[]) => {
      this.productsByUser = response;
      this.totalSum = this.calculateTotalPrice(this.productsByUser);
    });
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
