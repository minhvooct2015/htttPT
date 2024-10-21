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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_SP")
    private Long maSP;

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

}
