package simulation.client;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import simulation.client.model.DonHangDTO;
import simulation.client.model.SanPhamCuaDonHangDTO;

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
    @Path("donhang/dssp/{maNguoiDung}")
    List<SanPhamCuaDonHangDTO> getDsspByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung, @HeaderParam("Authorization") String authHeader);
    
}
