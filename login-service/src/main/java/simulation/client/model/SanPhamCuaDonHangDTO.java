package simulation.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamCuaDonHangDTO {

    private String maSP;
    private String tenSP;
    private String moTa;
    private Double giaSP;
    private Integer soLuongTonKho;
    private BigDecimal tongTien;
    private String hinhSP;
    private String loaiSP;
    private TrangThaiDonHang trangThaiDonHang;
    private double phiGiaoHang;
    private LocalDate ngayDat;
    private LocalDate ngayThanhToan;

    private ChiTietDonHangDTO chiTietDonHangDTO;
}
