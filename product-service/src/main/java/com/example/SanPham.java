package com.example;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "San_Pham")
@Getter
@Setter
public class SanPham {

    @Id
    @Column(name = "Ma_SP")
    private String maSP;

    @Column(name = "Ten_SP", nullable = false)
    private String tenSP;

    @Column(name = "Mo_ta")
    private String moTa;

    @Column(name = "Gia_SP", nullable = false)
    private Double giaSP;

    @Column(name = "SL_ton_kho")
    private Integer soLuongTonKho;

    @Column(name = "Hinh_SP")
    private String hinhSP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ma_danh_muc", nullable = false)
    private LoaiSanPham loaiSanPham;

    @PrePersist
    public void generateMaDanhMuc() {
        if (this.maSP == null || this.maSP.isEmpty()) {
            this.maSP = "SP" + String.format("%03d", (int) (Math.random() * 1000));
        }
    }

}
