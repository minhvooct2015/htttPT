package com.example.service;

import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import com.example.DonHang;
@ApplicationScoped
public class DonHangService {

    @Inject
     DonHangRepository donHangRepository;

    public DonHang addDonHang(DonHang donHang) {
        donHangRepository.persist(donHang);
        return donHang;
    }

    public DonHang editDonHang(Long id, DonHang updatedDonHang) {
        DonHang donHang = donHangRepository.findById(id);
        if (donHang != null) {
            donHang.setMaNguoiDung(updatedDonHang.getMaNguoiDung());
            donHang.setNgayDatHang(updatedDonHang.getNgayDatHang());
            donHang.setTongTien(updatedDonHang.getTongTien());
            donHang.setTrangThai(updatedDonHang.getTrangThai());
            donHang.setHoTen(updatedDonHang.getHoTen());
            donHangRepository.persist(donHang);
        }
        return donHang;
    }

    public boolean deleteDonHang(Long id) {
        return donHangRepository.deleteById(id);
    }

    public List<DonHang> listAll() {
        return donHangRepository.listAll();
    }
}

