package com.example;

import com.example.service.PKGenerationService;
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
    @Column(name = "Ma_danh_gia")
    private String maDanhGia;

    @Column(name = "Ma_nguoi_dung")
    private String maNguoiDung;

    @Column(name = "Noi_dung")
    private String noiDung;

    @Column(name = "Ma_SP")
    private String maSp;

    @Column(name = "Ngay_danh_gia")
    private LocalDate ngayDanhGia;

    @PrePersist
    public void generatePK() {
        if (this.maDanhGia == null || this.maDanhGia.isEmpty()) {
            this.maDanhGia = PKGenerationService.pkGen("DANHGIA");
        }
    }
}

