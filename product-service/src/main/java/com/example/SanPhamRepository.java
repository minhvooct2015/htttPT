package com.example;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SanPhamRepository implements PanacheRepository<SanPham> {
    // Additional custom queries can be added here

    public SanPham findByMaSP(String maSP) {
        return find("maSP", maSP).firstResult();
    }

    public List<SanPham> findByDSMaSP(List<String> maSP) {
        return list("maSP in ?1", maSP);
    }

    public void deleteByMaSP(String maSP) {
         delete("maSP", maSP);
    }
}
