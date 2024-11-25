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
  soLuongTonKho: number;
  hinhSP: string;
  loaiSP: string;
  chiTietDonHangDTO: ChiTietDonHangDTO;  // Nested ChiTietDonHangDTO
}
