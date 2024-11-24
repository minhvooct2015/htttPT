package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DanhGiaDTO {
    private String maDanhGia;
    private Long maNguoiDung;
    private String noiDung;
    private Long maSp;
    private LocalDate ngayDanhGia;
}