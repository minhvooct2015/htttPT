package com.example.service;

import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import com.example.DonHang;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DonHangService {

    @Inject
     DonHangRepository donHangRepository;

    @Transactional
    public DonHang addDonHang(DonHang donHang) {
        donHangRepository.persist(donHang);
        return donHang;
    }

    public List<DonHang> getDonHangByMaNguoiDung(String maNguoiDung) {
        return donHangRepository.findByMaNguoiDung(maNguoiDung);
    }

    public DonHang getDonHangById(String id) {
        DonHang donhang = donHangRepository.findById(id);
        if (donhang == null) throw new NotFoundException("Khong co don hang " + id);
        return donhang;
    }

    @Transactional
    public DonHang editDonHang(String id, DonHang updatedDonHang) {
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

    public boolean deleteDonHang(String id) {
        return donHangRepository.deleteById(id);
    }

    public List<DonHang> listAll() {
        return donHangRepository.listAll();
    }
}

