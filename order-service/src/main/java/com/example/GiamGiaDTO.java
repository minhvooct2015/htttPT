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
public class GiamGiaDTO {

    private String maGiamGia;
    private String code;
    private Double tyLeGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
}