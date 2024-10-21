package com.example.service;

import com.example.ChiTietDonHang;
import com.example.repo.ChiTietDonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ChiTietDonHangService {

    @Inject
    ChiTietDonHangRepository chiTietDonHangRepository;

    public ChiTietDonHang addChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        chiTietDonHangRepository.persist(chiTietDonHang);
        return chiTietDonHang;
    }

    public ChiTietDonHang editChiTietDonHang(Long id, ChiTietDonHang updatedChiTiet) {
        ChiTietDonHang chiTiet = chiTietDonHangRepository.findById(id);
        if (chiTiet != null) {
            chiTiet.setMaDh(updatedChiTiet.getMaDh());
            chiTiet.setMaSp(updatedChiTiet.getMaSp());
            chiTiet.setSoLuong(updatedChiTiet.getSoLuong());
            chiTiet.setThanhTien(updatedChiTiet.getThanhTien());
            chiTietDonHangRepository.persist(chiTiet);
        }
        return chiTiet;
    }

    public boolean deleteChiTietDonHang(Long id) {
        return chiTietDonHangRepository.deleteById(id);
    }

    public List<ChiTietDonHang> listAll() {
        return chiTietDonHangRepository.listAll();
    }
}
