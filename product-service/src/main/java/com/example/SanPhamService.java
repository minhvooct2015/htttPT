package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SanPhamService {

    @Inject
    SanPhamRepository sanPhamRepository;

    @Inject
    LoaiSanPhamRepository loaiSanPhamRepository;

    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.listAll();
    }

    public SanPham getSanPhamById(Long id) {
        return sanPhamRepository.findById(id);
    }

    @Transactional
    public void addSanPham(SanPham sanPham, Long loaiSanPhamId) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(loaiSanPhamId);
        if (loaiSanPham != null) {
            sanPham.setLoaiSanPham(loaiSanPham);
            sanPhamRepository.persist(sanPham);
        }
    }

    @Transactional
    public void updateSanPham(Long id, SanPham sanPham, Long loaiSanPhamId) {
        SanPham existing = sanPhamRepository.findById(id);
        if (existing != null) {
            LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(loaiSanPhamId);
            if (loaiSanPham != null) {
                existing.setTenSP(sanPham.getTenSP());
                existing.setMoTa(sanPham.getMoTa());
                existing.setGiaSP(sanPham.getGiaSP());
                existing.setSoLuongTonKho(sanPham.getSoLuongTonKho());
                existing.setHinhSP(sanPham.getHinhSP());
                existing.setLoaiSanPham(loaiSanPham);
                sanPhamRepository.persist(existing);
            }
        }
    }

    @Transactional
    public void deleteSanPham(Long id) {
        sanPhamRepository.deleteById(id);
    }
}

