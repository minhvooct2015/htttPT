package com.example;

import com.example.enumss.PhuongThucGiaoHang;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Giao_Hang")
@Getter
@Setter
public class GiaoHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_giao_hang")
    private Long maGiaoHang;

    @Column(name = "Ten_phuong_thuc")
    @Enumerated(EnumType.STRING)
    private PhuongThucGiaoHang tenPhuongThuc;

    @Column(name = "Phi_giao_hang")
    private Double phiGiaoHang;

    @Column(name = "Thoi_gian_du_kien")
    private String thoiGianDuKien;

}

