package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Loai_San_Pham")
@Getter
@Setter
public class LoaiSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_danh_muc")
    private Long maDanhMuc;

    @Column(name = "Ten_danh_muc", nullable = false)
    private String tenDanhMuc;

    @Column(name = "Mo_ta")
    private String moTa;

    @OneToMany(mappedBy = "loaiSanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPham> sanPhams;

}
