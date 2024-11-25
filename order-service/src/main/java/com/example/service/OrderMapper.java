package com.example.service;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;
import com.example.DonHang;
import com.example.DonHangDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        dto.setMaDh(Optional.ofNullable(entity.getDonHang()).map(DonHang::getMaDh).orElse(null));
        dto.setSoLuong(entity.getSoLuong());
        dto.setThanhTien(entity.getThanhTien());

        return dto;
    }

    public static DonHang dtoToEntityDonHang(DonHangDTO dto) {
        if (dto == null) {
            return null;
        }

        DonHang entity = new DonHang();
        entity.setMaDh(dto.getMaDh());
        entity.setMaNguoiDung(dto.getMaNguoiDung()); // Convert Long to String if needed
        entity.setNgayDatHang(dto.getNgayDatHang());
        entity.setTongTien(dto.getTongTien());
        entity.setTrangThai(dto.getTrangThai());
        entity.setPhuongThucGiaoHang(dto.getPhuongThucGiaoHang());
        entity.setPhiGiaoHang(dto.getPhiGiaoHang());
        entity.setThoiGianDuKien(dto.getThoiGianDuKien());
        entity.setPhuongThucThanhToan(dto.getPhuongThucThanhToan());
        entity.setNgayThanhToan(dto.getNgayThanhToan());

        // Handle chiTietDonHangs if needed
         List<ChiTietDonHangDTO> dsCTDH = dto.getDsCTDH();
        List<ChiTietDonHang> dsCTDHEntity = dsCTDH.stream()
                .map(OrderMapper::dtoToEntityCTDH)
                .peek(chiTietDonHang -> chiTietDonHang.setDonHang(entity))
                .collect(Collectors.toList());
        entity.setChiTietDonHangs(dsCTDHEntity);
        return entity;
    }

    // Method to convert DonHang entity to DonHangDTO
    public static DonHangDTO entityToDtoDonHang(DonHang entity) {
        if (entity == null) {
            return null;
        }

        DonHangDTO dto = new DonHangDTO();
        dto.setMaDh(entity.getMaDh());
        dto.setMaNguoiDung(dto.getMaNguoiDung()); // Convert String to Long if needed
        dto.setNgayDatHang(entity.getNgayDatHang());
        dto.setTongTien(entity.getTongTien());
        dto.setTrangThai(entity.getTrangThai());
        dto.setPhuongThucGiaoHang(entity.getPhuongThucGiaoHang());
        dto.setPhiGiaoHang(entity.getPhiGiaoHang());
        dto.setThoiGianDuKien(entity.getThoiGianDuKien());
        dto.setPhuongThucThanhToan(entity.getPhuongThucThanhToan());
        dto.setNgayThanhToan(entity.getNgayThanhToan());

//         If you need to handle chiTietDonHangs:
         List<ChiTietDonHangDTO> chiTietDTOs = entity.getChiTietDonHangs().stream()
                .map(OrderMapper::entityToDtoCTDH)
                .collect(Collectors.toList());
         dto.setDsCTDH(chiTietDTOs);

        return dto;
    }

}
