package com.example.repo;

import com.example.ThanhToan;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ThanhToanRepository implements PanacheRepository<ThanhToan> {
    // Custom query methods can be added here
}

