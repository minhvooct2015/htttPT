package com.example;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SanPhamRepository implements PanacheRepository<SanPham> {
    // Additional custom queries can be added here
}
