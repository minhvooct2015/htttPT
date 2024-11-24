//package com.example.service;
//
//import com.example.GiaoHang;
//import com.example.repo.GiaoHangRepository;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import java.util.List;
//
//@ApplicationScoped
//public class GiaoHangService {
//
//    @Inject
//    GiaoHangRepository giaoHangRepository;
//
//    public GiaoHang addGiaoHang(GiaoHang giaoHang) {
//        giaoHang.setMaGiaoHang(null);
//        giaoHangRepository.persist(giaoHang);
//        return giaoHang;
//    }
//
//    public GiaoHang editGiaoHang(Long id, GiaoHang updatedGiaoHang) {
//        GiaoHang giaoHang = giaoHangRepository.findById(id);
//        if (giaoHang != null) {
//            giaoHang.setTenPhuongThuc(updatedGiaoHang.getTenPhuongThuc());
//            giaoHang.setPhiGiaoHang(updatedGiaoHang.getPhiGiaoHang());
//            giaoHang.setThoiGianDuKien(updatedGiaoHang.getThoiGianDuKien());
//            giaoHangRepository.persist(giaoHang);
//        }
//        return giaoHang;
//    }
//
//    public boolean deleteGiaoHang(Long id) {
//        return giaoHangRepository.deleteById(id);
//    }
//
//    public List<GiaoHang> listAll() {
//        return giaoHangRepository.listAll();
//    }
//}
//
