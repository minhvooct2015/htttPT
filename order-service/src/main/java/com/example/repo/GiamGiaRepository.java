//package com.example.repo;
//
//import com.example.GiamGia;
//import io.quarkus.hibernate.orm.panache.PanacheRepository;
//import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
//import jakarta.enterprise.context.ApplicationScoped;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@ApplicationScoped
//public class GiamGiaRepository implements PanacheRepositoryBase<GiamGia, String> {
//    // Custom query methods can be added here
//    public GiamGia findByCode(String code) {
//        return find("code", code).firstResult();
//    }
//
//    // Find all active discounts
//    public List<GiamGia> findActiveDiscounts(LocalDate today) {
//        return list("ngayBatDau <= ?1 and ngayKetThuc >= ?1", today);
//    }
//}
//
