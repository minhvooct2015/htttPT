package com.example.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoaiSanPhamDTO {
    private String maDanhMuc;
    private String tenDanhMuc;
    private String moTa;
}