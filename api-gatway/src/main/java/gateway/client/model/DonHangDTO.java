package gateway.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonHangDTO {

    private String maDh;
    private String maNguoiDung;
    private LocalDate ngayDatHang;
    private BigDecimal tongTien;
    private TrangThaiDonHang trangThai;
    private String hoTen;
    private Double phiGiaoHang;
    private LocalDate thoiGianDuKien;
    private LocalDate ngayThanhToan;
    private List<ChiTietDonHangDTO> dsCTDH;

}