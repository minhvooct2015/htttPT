package com.example;

import jakarta.enterprise.context.ApplicationScoped;

public class SanphamMapper {

    public static SanPhamDTO toDTO(SanPham sanPham) {
        if (sanPham == null) {
            return null;
        }

        SanPhamDTO sanPhamDTO = new SanPhamDTO(// Directly map maSP
                sanPham.getTenSP(),              // Directly map tenSP
                sanPham.getMoTa(),               // Directly map moTa
                sanPham.getGiaSP(),              // Directly map giaSP
                sanPham.getSoLuongTonKho(),      // Directly map soLuongTonKho
                sanPham.getHinhSP() ,
                sanPham.getLoaiSanPham().getTenDanhMuc());            // Directly map hinhSP);
        return sanPhamDTO;
    }

    public static SanPham toEntity(SanPhamDTO sanPhamDTO) {
        if (sanPhamDTO == null) {
            return null;
        }

        SanPham sanPham = new SanPham();// Directly map maSP
        sanPham.setTenSP(sanPhamDTO.getTenSP());    // Directly map tenSP
        sanPham.setMoTa(sanPhamDTO.getMoTa());      // Directly map moTa
        sanPham.setGiaSP(sanPhamDTO.getGiaSP());    // Directly map giaSP
        sanPham.setSoLuongTonKho(sanPhamDTO.getSoLuongTonKho()); // Directly map soLuongTonKho
//        sanPham.setHinhSP(sanPhamDTO.getHinhSP());  // Directly map hinhSP

        // You may need to handle the nested mapping (LoaiSanPham) here, if you want to set it.
        // Example: You can fetch LoaiSanPham based on maDanhMuc if needed.
//        LoaiSanPham loaiSanPham = new LoaiSanPham();
//        loaiSanPham.setMaDanhMuc(sanPhamDTO.getMaDanhMuc()); // Assuming you have a way to set it
//        sanPham.setLoaiSanPham(loaiSanPham);

        return sanPham;
    }

}
