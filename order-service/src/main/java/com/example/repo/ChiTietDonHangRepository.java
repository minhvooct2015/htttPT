package com.example.repo;

import com.example.ChiTietDonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChiTietDonHangRepository implements PanacheRepositoryBase<ChiTietDonHang, String> {
    // Custom methods if needed
    public ChiTietDonHang findByMaDHAndMaSP(String maDH, Long maSP) {
        return find("maDh = ?1 and maSp = ?2", maDH, maSP).firstResult();
    }

}
