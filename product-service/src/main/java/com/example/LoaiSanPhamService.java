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

    public LoaiSanPham getLoaiSanPhamById(String id) {
        return loaiSanPhamRepository.findByIdLSP(id);
    }

    @Transactional
    public void addLoaiSanPham(LoaiSanPhamDTO loaiSanPhamdto) {
        LoaiSanPham loaisp = LoaiSpMapper.toEntity(loaiSanPhamdto);
        loaiSanPhamRepository.persist(loaisp);
    }

    @Transactional
    public void updateLoaiSanPham(String id, LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPham existing = loaiSanPhamRepository.findByIdLSP(id);
        if (existing != null) {
            existing.setTenDanhMuc(loaiSanPhamDTO.getTenDanhMuc());
            existing.setMoTa(loaiSanPhamDTO.getMoTa());
            loaiSanPhamRepository.persist(existing);
        }
    }

    @Transactional
    public void deleteLoaiSanPham(String id) {
        loaiSanPhamRepository.deleteByIdSP(id);
    }
}

