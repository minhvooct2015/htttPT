package com.example;

import com.example.enumss.PhuongThucThanhToan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Thanh_Toan")
@Getter
@Setter
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ma_thanh_toan")
    private Long maThanhToan;

    @Column(name = "Phuong_thuc_thanh_toan")
    @Enumerated(EnumType.STRING)
    private PhuongThucThanhToan phuongThucThanhToan;

    @Column(name = "Ngay_thanh_toan")
    private LocalDate ngayThanhToan;

}
