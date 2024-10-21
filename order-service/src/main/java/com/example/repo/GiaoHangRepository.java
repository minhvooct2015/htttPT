package com.example.repo;

import com.example.GiaoHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GiaoHangRepository implements PanacheRepository<GiaoHang> {
    // Custom query methods can be added here
}

