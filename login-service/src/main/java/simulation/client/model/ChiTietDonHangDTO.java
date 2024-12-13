package simulation.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDonHangDTO {

    private String maCtdh;
    private String maDh;
    private String maSp;
    private Integer soLuong;
    private BigDecimal thanhTien;
}