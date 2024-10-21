package com.example.repo;

import com.example.GiamGia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GiamGiaRepository implements PanacheRepository<GiamGia> {
    // Custom query methods can be added here
}

