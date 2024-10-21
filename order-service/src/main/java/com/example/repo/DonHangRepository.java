package com.example.repo;

import com.example.DonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DonHangRepository implements PanacheRepository<DonHang> {
    // Custom query methods can be added here
}

