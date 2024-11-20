package com.example;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoaiSanPhamRepository implements PanacheRepository<LoaiSanPham> {
    // Additional custom queries can be added here



    public LoaiSanPham findByIdLSP(String id) {
        return find("maDanhMuc",id).firstResult();
    }

    public long deleteByIdSP(String id) {
        return delete("maDanhMuc", id);
    }
}
