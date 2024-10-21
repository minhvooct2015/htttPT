package com.example.service;

import com.example.ThanhToan;
import com.example.repo.ThanhToanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ThanhToanService {

    @Inject
    ThanhToanRepository thanhToanRepository;

    public ThanhToan addThanhToan(ThanhToan thanhToan) {
        thanhToanRepository.persist(thanhToan);
        return thanhToan;
    }

    public ThanhToan editThanhToan(Long id, ThanhToan updatedThanhToan) {
        ThanhToan thanhToan = thanhToanRepository.findById(id);
        if (thanhToan != null) {
            thanhToan.setPhuongThucThanhToan(updatedThanhToan.getPhuongThucThanhToan());
            thanhToan.setNgayThanhToan(updatedThanhToan.getNgayThanhToan());
            thanhToanRepository.persist(thanhToan);
        }
        return thanhToan;
    }

    public boolean deleteThanhToan(Long id) {
        return thanhToanRepository.deleteById(id);
    }

    public List<ThanhToan> listAll() {
        return thanhToanRepository.listAll();
    }
}

