export interface ChiTietDonHangDTO {
  maCtdh: string;
  maDh: string;
  maSp: string;
  soLuong: number;
  thanhTien: number;
}


export interface Product {
  maSP: string;
  tenSP: string;
  moTa: string;
  giaSP: number;
  phiGiaoHang: number;
  soLuongTonKho: number;
  hinhSP: string;
  loaiSP: string;
  trangThaiDonHang: TrangThaiDonHang;
  chiTietDonHangDTO: ChiTietDonHangDTO;  // Nested ChiTietDonHangDTO
}

// don-hang.model.ts
export interface DonHang {
  // maDh: string; // Order ID
  // maNguoiDung: string; // User ID
  // ngayDatHang: Date; // Order date
  // tongTien: number; // Total amount
  // trangThai: string; // Order status
  // hoTen: string; // Full name
  // phuongThucGiaoHang: string; // Shipping method
  // phiGiaoHang: number; // Shipping fee
  // thoiGianDuKien: Date; // Estimated delivery time
  // phuongThucThanhToan: string; // Payment method
  // ngayThanhToan: Date; // Payment date
  trangThai: TrangThaiDonHang
}

// Enum for order status
export enum TrangThaiDonHang {
  DANG_DAT = 'DANG_DAT',         // Order is placed
  DANG_XU_LY = 'DANG_XU_LY',     // Order is being processed
  DA_GIAO = 'DA_GIAO',           // Order is delivered
  HUY = 'HUY'                     // Order is canceled
}
