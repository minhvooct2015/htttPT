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
        danhGiaRepository.persist(danhGia);
        return danhGia;
    }

    public DanhGia editDanhGia(Long id, DanhGia updatedDanhGia) {
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

    public boolean deleteDanhGia(Long id) {
        return danhGiaRepository.deleteById(id);
    }

    public List<DanhGia> listAll() {
        return danhGiaRepository.listAll();
    }
}

