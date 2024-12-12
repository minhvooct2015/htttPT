package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Quan_Huyen")
@Getter
@Setter
public class QuanHuyen {
    @Id
    @Column(name = "Ma_QH", length = 10)
    private String maQH;

    @Column(name = "Ten_QH", nullable = false)
    private String tenQH;

    @ManyToOne
    @JoinColumn(name = "Ma_TP", nullable = false)
    private TinhThanhPho tinhThanhPho;

}

