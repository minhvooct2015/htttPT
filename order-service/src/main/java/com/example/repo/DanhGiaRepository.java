//package com.example.repo;
//
//import com.example.DanhGia;
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
//import jakarta.enterprise.context.ApplicationScoped;
//
//import java.util.List;
//
//@ApplicationScoped
//public class DanhGiaRepository implements PanacheRepositoryBase<DanhGia, String> {
//    // Custom method to find reviews by user ID
//    public List<DanhGia> findByMaNguoiDung(String maNguoiDung) {
//        return list("maNguoiDung", maNguoiDung);
//    }
//
//    // Custom method to find reviews by product ID
//    public List<DanhGia> findByMaSp(String maSp) {
//        return list("maSp", maSp);
//    }
//}
