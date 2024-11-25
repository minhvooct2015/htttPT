package com.example.repo;

import com.example.ChiTietDonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ChiTietDonHangRepository implements PanacheRepositoryBase<ChiTietDonHang, String> {
    // Custom methods if needed
    public ChiTietDonHang findByMaDHAndMaSP(String maDH, Long maSP) {
        return find("maDh = ?1 and maSp = ?2", maDH, maSP).firstResult();
    }

    public List<ChiTietDonHang> findByMaNguoiDung(String maNguoiDung) {
        return list("SELECT c FROM ChiTietDonHang c JOIN c.donHang d WHERE d.maNguoiDung = ?1", maNguoiDung);
    }

}
