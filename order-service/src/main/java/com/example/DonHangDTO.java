package com.example;

import com.example.enumss.TrangThaiDonHang;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonHangDTO {

    private String maDh;
    private Long maNguoiDung;
    private LocalDate ngayDatHang;
    private BigDecimal tongTien;
    private TrangThaiDonHang trangThai;
    private String hoTen;
}