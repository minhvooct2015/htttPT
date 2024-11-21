package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.SanPhamResource.IMAGE_DIRECTORY;

public class SanphamMapper {

    public static SanPhamDTO toDTO(SanPham sanPham) {
        if (sanPham == null) {
            return null;
        }
        // Read the image as bytes
        String hinhSP = sanPham.getHinhSP();

        SanPhamDTO sanPhamDTO = new SanPhamDTO(
                sanPham.getMaSP(),// Directly map maSP
                sanPham.getTenSP(),              // Directly map tenSP
                sanPham.getMoTa(),               // Directly map moTa
                sanPham.getGiaSP(),              // Directly map giaSP
                sanPham.getSoLuongTonKho(),      // Directly map soLuongTonKho
                sanPham.getHinhSP() ,
                sanPham.getLoaiSanPham().getTenDanhMuc(),
                getBytes(hinhSP));            // Directly map hinhSP);
        return sanPhamDTO;
    }

    private static byte[] getBytes(String hinhSP) {
        byte[] imageData = null;
        if (hinhSP != null) {
            try {


//                Path imagePath = Paths.get(hinhSP);
//                if (!Files.exists(imagePath)) {
//                   return new byte[0];
//                }
//                imageData = Files.readAllBytes(imagePath);
                return new byte[0];
            } catch (Exception e) {
                e.printStackTrace();  // Handle exception accordingly
            }
        }
        return imageData;
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
