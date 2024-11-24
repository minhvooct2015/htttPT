package com.example;

import com.example.enumss.PhuongThucGiaoHang;
import com.example.enumss.PhuongThucThanhToan;
import com.example.enumss.TrangThaiDonHang;
import com.example.service.PKGenerationService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Don_Hang")
@Getter
@Setter
public class DonHang {

    @Id
    @Column(name = "Ma_DH")
    private String maDh;

    @Column(name = "Ma_nguoi_dung")
    private String maNguoiDung;

    @Column(name = "Ngay_dat_hang")
    private LocalDate ngayDatHang;

    @Column(name = "Tong_tien")
    private BigDecimal tongTien;

    @Column(name = "Trang_thai")
    @Enumerated(EnumType.STRING)
    private TrangThaiDonHang trangThai;

    @Column(name = "Ten_phuong_thuc_gd")
    @Enumerated(EnumType.STRING)
    private PhuongThucGiaoHang phuongThucGiaoHang;

    @Column(name = "Phi_giao_hang")
    private Double phiGiaoHang;

    @Column(name = "Thoi_gian_du_kien")
    private LocalDate thoiGianDuKien;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHangs;

    @Column(name = "Phuong_thuc_thanh_toan")
    @Enumerated(EnumType.STRING)
    private PhuongThucThanhToan phuongThucThanhToan;

    @Column(name = "Ngay_thanh_toan")
    private LocalDate ngayThanhToan;
    @PrePersist
    public void generatePK() {
        if (this.maDh == null || this.maDh.isEmpty()) {
            this.maDh = PKGenerationService.pkGen("MADH");
        }
    }
}

