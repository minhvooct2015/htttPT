package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LoaiSanPhamService {

    @Inject
    LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPham> getAllLoaiSanPham() {
        return loaiSanPhamRepository.listAll();
    }

    public LoaiSanPham getLoaiSanPhamById(Long id) {
        return loaiSanPhamRepository.findById(id);
    }

    @Transactional
    public void addLoaiSanPham(LoaiSanPham loaiSanPham) {
        loaiSanPhamRepository.persist(loaiSanPham);
    }

    @Transactional
    public void updateLoaiSanPham(Long id, LoaiSanPham loaiSanPham) {
        LoaiSanPham existing = loaiSanPhamRepository.findById(id);
        if (existing != null) {
            existing.setTenDanhMuc(loaiSanPham.getTenDanhMuc());
            existing.setMoTa(loaiSanPham.getMoTa());
            loaiSanPhamRepository.persist(existing);
        }
    }

    @Transactional
    public void deleteLoaiSanPham(Long id) {
        loaiSanPhamRepository.deleteById(id);
    }
}

