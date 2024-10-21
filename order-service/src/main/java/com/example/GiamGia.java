package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Giam_Gia")
@Getter
@Setter
public class GiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_giam_gia")
    private Long maGiamGia;

    @Column(name = "Code")
    private String code;

    @Column(name = "Ty_le_giam")
    private Double tyLeGiam;

    @Column(name = "Ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "Ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    // Getters and Setters
}
