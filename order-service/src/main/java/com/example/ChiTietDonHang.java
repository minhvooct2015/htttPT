package com.example;

import com.example.service.PKGenerationService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Chi_Tiet_Don_Hang")
@Getter
@Setter
public class ChiTietDonHang {

    @Id
    @Column(name = "Ma_CTDH")
    private String maCtdh;

    @ManyToOne
    @JoinColumn(name = "Ma_DH", referencedColumnName = "Ma_DH")
    private DonHang donHang;

    @Column(name = "Ma_SP")
    private String maSp;

    @Column(name = "So_luong")
    private Integer soLuong;

    @Column(name = "Thanh_tien")
    private BigDecimal thanhTien;

    @PrePersist
    public void generatePK() {
        if (this.maCtdh == null || this.maCtdh.isEmpty()) {
            this.maCtdh = PKGenerationService.pkGen("CTDH");
        }
    }
}

