package com.example.service;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;

public class OrderMapper {

    public static ChiTietDonHang dtoToEntityCTDH(ChiTietDonHangDTO dto) {
        if (dto == null) {
            return null;
        }

        ChiTietDonHang entity = new ChiTietDonHang();
//        entity.setMaCtdh(String.valueOf(dto.getMaCtdh())); // Convert Long to String if needed
//        entity.setMaDh(dto.getMaDh());
        entity.setMaSp(dto.getMaSp());
        entity.setSoLuong(dto.getSoLuong());
        entity.setThanhTien(dto.getThanhTien());

        return entity;
    }

    public static ChiTietDonHangDTO entityToDtoCTDH(ChiTietDonHang entity) {
        if (entity == null) {
            return null;
        }

        ChiTietDonHangDTO dto = new ChiTietDonHangDTO();
        dto.setMaCtdh(entity.getMaCtdh());
        dto.setMaSp(entity.getMaSp());
        dto.setSoLuong(entity.getSoLuong());
        dto.setThanhTien(entity.getThanhTien());

        return dto;
    }
}
