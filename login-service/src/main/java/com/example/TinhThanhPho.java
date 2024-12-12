package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tinh_Thanh_Pho")
@Getter
@Setter
public class TinhThanhPho {
    @Id
    @Column(name = "Ma_TP", length = 10)
    private String maTP;

    @Column(name = "Ten_TP", nullable = false)
    private String tenTP;

    // Getters and setters
}
