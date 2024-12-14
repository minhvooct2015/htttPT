package simulation;

import com.example.LoginService;
import com.example.UserEntity;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import simulation.client.OrderClient;
import simulation.client.ProductServiceClient;
import simulation.client.model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@ApplicationScoped
public class SimulationService {

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    @Inject
    @RestClient
    OrderClient orderClient;


    @Inject
    LoginService loginService;

    public void one1()  {
        String user = "string1";
        String login = login(user);
        UserEntity userEntity = loginService.findByTK(user);

        String jwt = "Bearer " + login;
        List<SanPhamDTO> allSP = productServiceClient.getAllSP(jwt);
        Optional<SanPhamDTO> sanpham = allSP.stream().filter(sp -> sp.getSoLuongTonKho() > 0).findAny();
        String userNumber = userEntity.getMaNguoiDung();
        String maSp = sanpham.get().getMaSP();
        BigDecimal giaSP = BigDecimal.valueOf(sanpham.get().getGiaSP());

        // Build ChiTietDonHangDTO instance
        ChiTietDonHangDTO chiTietDonHang = ChiTietDonHangDTO.builder()
                .maSp(maSp)
                .soLuong(1)
                .thanhTien(giaSP)
                .build();

        // Build DonHangDTO instance
        DonHangDTO donHang = DonHangDTO.builder()
//                .maDh(null) // Assuming the ID is auto-generated or not required initially
                .maNguoiDung(userEntity.getMaNguoiDung())
                .ngayDatHang(LocalDate.now())
                .tongTien(giaSP)
                .trangThai(TrangThaiDonHang.DANG_DAT) // Enum value
                .hoTen(null) // Replace with user’s name if available
                .phiGiaoHang(10000.0)
                .thoiGianDuKien(LocalDate.now()) // Current date
                .ngayThanhToan(LocalDate.now())  // Current date
                .dsCTDH(Collections.singletonList(chiTietDonHang)) // Single item list
                .build();

         orderClient.addDonHang(donHang, jwt);
        List<SanPhamCuaDonHangDTO> dsspByMaNguoiDung = orderClient.getDsspByMaNguoiDung(userNumber, jwt);
        SanPhamCuaDonHangDTO sanPhamCuaDonHangDTO = dsspByMaNguoiDung.get(0);
        double giaTien = sanPhamCuaDonHangDTO.getGiaSP() * sanPhamCuaDonHangDTO.getChiTietDonHangDTO().getSoLuong();
        DonHangDTO checkoutDTO = DonHangDTO.builder().tongTien(BigDecimal.valueOf(giaTien)).trangThai(TrangThaiDonHang.DANG_XU_LY).build();
        orderClient.checkout(sanPhamCuaDonHangDTO.getChiTietDonHangDTO().getMaDh(), checkoutDTO, jwt);
    }

    //admin approve
    public List<SanPhamCuaDonHangDTO>  one(){
        String user = "admin";
        String login = login(user);

        String jwt = "Bearer " + login;
        List<SanPhamCuaDonHangDTO> allSP = orderClient.getAllDonHangDangXL(jwt);
        List<SanPhamCuaDonHangDTO> collect = allSP.stream().filter(sanPhamCuaDonHangDTO -> {
            LocalDate ngayDat = sanPhamCuaDonHangDTO.getNgayDat();
            LocalDate now = LocalDate.now().minusDays(3);
            boolean isBefor3days = ngayDat.isBefore(now);
            return isBefor3days;
        }).collect(Collectors.toList());
//        return collect;
        if(collect.isEmpty()) return Collections.emptyList();
        DonHangDTO checkoutDTO = DonHangDTO.builder().trangThai(TrangThaiDonHang.DA_GIAO).build();
        orderClient.checkout(collect.get(0).getChiTietDonHangDTO().getMaDh(), checkoutDTO, jwt);
        return collect;
    }
    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // Simulate 5 users
        for (int i = 0; i < 5; i++) {
            String userId = "string" + 1;
            CompletableFuture.runAsync(() -> simulateUserWorkflow(userId), executorService);
        }

        executorService.shutdown();
    };

    private  void simulateUserWorkflow(String userId) {
        try {
            // 1. Login and get JWT token
//            String jwtToken = login(userId);
            String login =  login(userId);
//            // 2. Call Order Service
//            CompletableFuture<Void> orderFuture = callOrderService(jwtToken, userId);
//
//            // 3. Call Product Service
//            CompletableFuture<Void> productFuture = callProductService(jwtToken, userId);

            // Wait for both services to complete
//            CompletableFuture.allOf(orderFuture, productFuture).join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String login(String userId) {
        String loginPayload = "{\"username\":\"user" + userId + "\",\"password\":\"password\"}";


        String taiKhoan = "1";
        UserEntity user = loginService.login(userId, "string");

        // Here you would generate a JWT token and return it
        // Example: return generateToken(user);
        String token = Jwt.issuer("http://localhost:9000")  // Match `mp.jwt.verify.issuer`
                .upn(taiKhoan)
                .groups(new HashSet<>(Set.of(user.getVaiTro().toString()))) // Add user roles
                .claim("email", user.getEmail())
                .claim("userNumber", user.getMaNguoiDung())
                .claim("tenUser", user.getHoTen())
                .expiresAt(Instant.now().plus(3, ChronoUnit.HOURS)) // Token expires in 1 hour
// Custom claim
                .sign();
        return token;
    }

//    private  CompletableFuture<Void> callOrderService(String jwtToken, int userId) {
//        return CompletableFuture.runAsync(() -> {
//            try {
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(ORDER_SERVICE_URL + "/user/" + userId))
//                        .header("Authorization", "Bearer " + jwtToken)
//                        .GET()
//                        .build();
//
//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                System.out.println("Order service response for user " + userId + ": " + response.body());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private  CompletableFuture<Void> callProductService(String jwtToken, int userId) {
//        return CompletableFuture.runAsync(() -> {
//            try {
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(PRODUCT_SERVICE_URL + "/user/" + userId))
//                        .header("Authorization", "Bearer " + jwtToken)
//                        .GET()
//                        .build();
//
//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                System.out.println("Product service response for user " + userId + ": " + response.body());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
