package com.example.repo;

import com.example.ChiTietDonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChiTietDonHangRepository implements PanacheRepository<ChiTietDonHang> {
    // Custom query methods can be added here
}
