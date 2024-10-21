package com.example.repo;

import com.example.DanhGia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DanhGiaRepository implements PanacheRepository<DanhGia> {
    // Custom query methods can be added here
}
