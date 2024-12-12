package com.example;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Phuong_Xa")
@Getter
@Setter
public class PhuongXa {
    @Id
    @Column(name = "Ma_PX", length = 10)
    private String maPX;

    @Column(name = "Ten_PX", nullable = false)
    private String tenPX;

    @Column(name = "Ten_Tieng_Anh")
    private String tenTiengAnh;

    @Column(name = "Cap", nullable = false)
    private String cap;

    @ManyToOne
    @JoinColumn(name = "Ma_QH", nullable = false)
    private QuanHuyen quanHuyen;

    // Getters and setters
}

