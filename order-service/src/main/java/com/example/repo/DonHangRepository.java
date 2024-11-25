package com.example.repo;

import com.example.DonHang;
import com.example.enumss.TrangThaiDonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DonHangRepository implements PanacheRepositoryBase<DonHang, String> {
    // Custom query methods can be added here

    // Custom query to find orders by user ID
    public List<DonHang> findByMaNguoiDung(String maNguoiDung) {
        return list("maNguoiDung", maNguoiDung);
    }

    public List<DonHang> findDHDaDatByMaNguoiDung(String maNguoiDung) {
        return list("maNguoiDung = ?1 and trangThai = ?2",
                maNguoiDung, TrangThaiDonHang.DANG_DAT);
    }

}

