package com.example.service;

import com.example.DonHangDTO;
import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import com.example.DonHang;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DonHangService {

    @Inject
     DonHangRepository donHangRepository;

    @Transactional
    public DonHang addDonHang(DonHang donHang) {
        donHangRepository.persist(donHang);
        return donHang;
    }

    public List<DonHangDTO> getDonHangByMaNguoiDung(String maNguoiDung) {
        List<DonHang> donHangList = donHangRepository.findByMaNguoiDung(maNguoiDung);
        return donHangList.stream().map(OrderMapper::entityToDtoDonHang)
                .collect(Collectors.toList());
    }

    public DonHangDTO getDonHangById(String id) {
        DonHang donhang = donHangRepository.findById(id);

        if (donhang == null) throw new NotFoundException("Khong co don hang " + id);
        DonHangDTO donHangDTO = OrderMapper.entityToDtoDonHang(donhang);
        return donHangDTO;
    }

    @Transactional
    public void editDonHang(String id, DonHangDTO donHangDTO) {
        // Find the existing DonHang entity by its ID (maDh)
        DonHang existingDonHang = donHangRepository.findById(id);

        // Update the fields of the existing entity with the values from DTO
        existingDonHang.setMaNguoiDung(donHangDTO.getMaNguoiDung());
        existingDonHang.setNgayDatHang(donHangDTO.getNgayDatHang());
        existingDonHang.setTongTien(donHangDTO.getTongTien());
        existingDonHang.setTrangThai(donHangDTO.getTrangThai());
        existingDonHang.setPhuongThucGiaoHang(donHangDTO.getPhuongThucGiaoHang());
        existingDonHang.setPhiGiaoHang(donHangDTO.getPhiGiaoHang());
        existingDonHang.setThoiGianDuKien(donHangDTO.getThoiGianDuKien());
        existingDonHang.setPhuongThucThanhToan(donHangDTO.getPhuongThucThanhToan());
        existingDonHang.setNgayThanhToan(donHangDTO.getNgayThanhToan());

        // Here you can handle the update of related entities such as ChiTietDonHang

        // Save and return the updated DonHang entity
         donHangRepository.persist(existingDonHang);
    }

    public boolean deleteDonHang(String id) {
        return donHangRepository.deleteById(id);
    }

    public List<DonHangDTO> listAll() {
        List<DonHang> donHangs = donHangRepository.listAll();
        return donHangs.stream().map(OrderMapper::entityToDtoDonHang)
                .collect(Collectors.toList());
    }
}

