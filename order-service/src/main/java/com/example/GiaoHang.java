//package com.example;
//
//import com.example.enumss.PhuongThucGiaoHang;
//import com.example.service.PKGenerationService;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//@Entity
//@Table(name = "Giao_Hang")
//@Getter
//@Setter
//@Deprecated
//public class GiaoHang {
//
//    @Id
//    @Column(name = "Ma_giao_hang")
//    private String maGiaoHang;
//
//    @Column(name = "Ten_phuong_thuc")
//    @Enumerated(EnumType.STRING)
//    private PhuongThucGiaoHang tenPhuongThuc;
//
//    @Column(name = "Phi_giao_hang")
//    private Double phiGiaoHang;
//
//    @Column(name = "Thoi_gian_du_kien")
//    private LocalDate thoiGianDuKien;
//
//    @PrePersist
//    public void generatePK() {
//        if (this.maGiaoHang == null || this.maGiaoHang.isEmpty()) {
//            this.maGiaoHang = PKGenerationService.pkGen("MAGH");
//        }
//    }
//}
//
