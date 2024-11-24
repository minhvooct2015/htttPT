package com.example;

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

    @Column(name = "Ho_ten")
    private String hoTen;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHangs;

    @PrePersist
    public void generatePK() {
        if (this.maDh == null || this.maDh.isEmpty()) {
            this.maDh = PKGenerationService.pkGen("MADH");
        }
    }
}

