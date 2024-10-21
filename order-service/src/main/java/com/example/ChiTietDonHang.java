package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Chi_Tiet_Don_Hang")
@Getter
@Setter
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_CTDH")
    private Long maCtdh;

    @Column(name = "Ma_DH")
    private Long maDh;

    @Column(name = "Ma_SP")
    private Long maSp;

    @Column(name = "So_luong")
    private Integer soLuong;

    @Column(name = "Thanh_tien")
    private BigDecimal thanhTien;

    // Getters and Setters
}

