package com.example.service;

import com.example.GiamGia;
import com.example.repo.GiamGiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GiamGiaService {

    @Inject
    GiamGiaRepository giamGiaRepository;

    public GiamGia addGiamGia(GiamGia giamGia) {
        giamGiaRepository.persist(giamGia);
        return giamGia;
    }

    public GiamGia editGiamGia(Long id, GiamGia updatedGiamGia) {
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

    public boolean deleteGiamGia(Long id) {
        return giamGiaRepository.deleteById(id);
    }

    public List<GiamGia> listAll() {
        return giamGiaRepository.listAll();
    }
}

