package gateway.client;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import gateway.client.model.DonHangDTO;
import gateway.client.model.SanPhamCuaDonHangDTO;

import java.util.List;

@RegisterRestClient
public interface OrderClient {

    @POST
    @Path("donhang")
    void addDonHang(@RequestBody DonHangDTO donHang, @HeaderParam("Authorization") String authHeader);

    @PUT
    @Path("donhang/checkout/{id}")
    void checkout(@PathParam("id") String id, DonHangDTO donHang, @HeaderParam("Authorization") String authHeader);

    @GET
    @Path("donhang/all-dssp/dang-xu-ly")
    List<SanPhamCuaDonHangDTO> getAllDonHangDangXL(@HeaderParam("Authorization") String authHeader);

    @GET
    @Path("donhang/dssp/{maNguoiDung}")
    List<SanPhamCuaDonHangDTO> getDsspByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung, @HeaderParam("Authorization") String authHeader);

}
