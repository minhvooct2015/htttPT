package com.example.DTOS;

import com.example.ChiTietDonHangDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamCuaDonHangDTO {

    private String maSP;
    private String tenSP;
    private String moTa;
    private Double giaSP;
    private Integer soLuongTonKho;

    private String hinhSP;
    private String loaiSP;

    private ChiTietDonHangDTO chiTietDonHangDTO;
}
