package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Danh_Gia")
@Getter
@Setter
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_danh_gia")
    private Long maDanhGia;

    @Column(name = "Ma_nguoi_dung")
    private Long maNguoiDung;

    @Column(name = "Noi_dung")
    private String noiDung;

    @Column(name = "Ma_SP")
    private Long maSp;

    @Column(name = "Ngay_danh_gia")
    private LocalDate ngayDanhGia;

    // Getters and Setters
}

