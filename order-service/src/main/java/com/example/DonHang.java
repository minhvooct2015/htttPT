package com.example;

import com.example.enumss.TrangThaiDonHang;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Don_Hang")
@Getter
@Setter
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_DH")
    private Long maDh;

    @Column(name = "Ma_nguoi_dung")
    private Long maNguoiDung;

    @Column(name = "Ngay_dat_hang")
    private LocalDate ngayDatHang;

    @Column(name = "Tong_tien")
    private BigDecimal tongTien;

    @Column(name = "Trang_thai")
    @Enumerated(EnumType.STRING)
    private TrangThaiDonHang trangThai;

    @Column(name = "Ho_ten")
    private String hoTen;
}

