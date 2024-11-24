package com.example.service;

import com.example.DanhGia;
import com.example.repo.DanhGiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DanhGiaService {

    @Inject
    DanhGiaRepository danhGiaRepository;

    public DanhGia addDanhGia(DanhGia danhGia) {
        danhGia.setMaDanhGia(null);
        danhGiaRepository.persist(danhGia);
        return danhGia;
    }

    public DanhGia editDanhGia(String id, DanhGia updatedDanhGia) {
        DanhGia danhGia = danhGiaRepository.findById(id);
        if (danhGia != null) {
            danhGia.setMaNguoiDung(updatedDanhGia.getMaNguoiDung());
            danhGia.setNoiDung(updatedDanhGia.getNoiDung());
            danhGia.setMaSp(updatedDanhGia.getMaSp());
            danhGia.setNgayDanhGia(updatedDanhGia.getNgayDanhGia());
            danhGiaRepository.persist(danhGia);
        }
        return danhGia;
    }

    public DanhGia getDanhGiaById(String id) {
        return danhGiaRepository.findById(id);
    }

    public List<DanhGia> getDanhGiaByMaNguoiDung(String maNguoiDung) {
        return danhGiaRepository.findByMaNguoiDung(maNguoiDung);
    }

    public List<DanhGia> getDanhGiaByMaSp(String maSp) {
        return danhGiaRepository.findByMaSp(maSp);
    }

    public boolean deleteDanhGia(String id) {
        return danhGiaRepository.deleteById(id);
    }

    public List<DanhGia> listAll() {
        return danhGiaRepository.listAll();
    }
}

