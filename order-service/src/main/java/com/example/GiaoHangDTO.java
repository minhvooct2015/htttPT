package com.example;


import com.example.enumss.PhuongThucGiaoHang;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiaoHangDTO {
    private String maGiaoHang;
    private PhuongThucGiaoHang tenPhuongThuc;
    private Double phiGiaoHang;
    private LocalDate thoiGianDuKien;
}
