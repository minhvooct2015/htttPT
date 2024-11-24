package com.example.service;

import com.example.GiamGia;
import com.example.repo.GiamGiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class GiamGiaService {

    @Inject
    GiamGiaRepository giamGiaRepository;

    public GiamGia addGiamGia(GiamGia giamGia) {
        giamGia.setMaGiamGia(null);
        giamGiaRepository.persist(giamGia);
        return giamGia;
    }

    public GiamGia editGiamGia(String id, GiamGia updatedGiamGia) {
        GiamGia giamGia = giamGiaRepository.findById(id);
        if (giamGia != null) {
            giamGia.setCode(updatedGiamGia.getCode());
            giamGia.setTyLeGiam(updatedGiamGia.getTyLeGiam());
            giamGia.setNgayBatDau(updatedGiamGia.getNgayBatDau());
            giamGia.setNgayKetThuc(updatedGiamGia.getNgayKetThuc());
            giamGiaRepository.persist(giamGia);
        }
        return giamGia;
    }

    public void updateGiamGia(String id, String code, Double tyLeGiam, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        GiamGia giamGia = giamGiaRepository.findById(id);
        if (giamGia != null) {
            giamGia.setCode(code);
            giamGia.setTyLeGiam(tyLeGiam);
            giamGia.setNgayBatDau(ngayBatDau);
            giamGia.setNgayKetThuc(ngayKetThuc);
            giamGiaRepository.persist(giamGia);
        }
    }

    public boolean deleteGiamGia(String id) {
        return giamGiaRepository.deleteById(id);
    }

    public List<GiamGia> listAll() {
        return giamGiaRepository.listAll();
    }

    public List<GiamGia> getActiveDiscounts(LocalDate today) {
        return giamGiaRepository.findActiveDiscounts(today);
    }
}

