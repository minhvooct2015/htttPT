package com.example;

import com.example.enumss.PhuongThucGiaoHang;
import com.example.enumss.PhuongThucThanhToan;
import com.example.enumss.TrangThaiDonHang;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonHangDTO {

    private String maDh;
    private String maNguoiDung;
    private LocalDate ngayDatHang;
    private BigDecimal tongTien;
    private TrangThaiDonHang trangThai;
    private String hoTen;
    private PhuongThucGiaoHang phuongThucGiaoHang;
    private Double phiGiaoHang;
    private LocalDate thoiGianDuKien;
    private PhuongThucThanhToan phuongThucThanhToan;
    private LocalDate ngayThanhToan;
    private List<ChiTietDonHangDTO> dsCTDH;

}