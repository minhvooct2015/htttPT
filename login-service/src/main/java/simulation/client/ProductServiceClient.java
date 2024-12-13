package simulation.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import simulation.client.model.Operation;
import simulation.client.model.SanPhamCuaDonHangDTO;
import simulation.client.model.SanPhamDTO;
import simulation.client.model.UpdateSLSPDTO;

import java.util.List;
import java.util.Set;

@RegisterRestClient
public interface ProductServiceClient {

    @GET
    @Path("san-pham/all")
    List<SanPhamDTO> getAllSP(@HeaderParam("Authorization") String authHeader);

    @GET
    @Path("san-pham/{id}")
    SanPhamDTO getAll(@PathParam("id") String id, @HeaderParam("Authorization") String authHeader);


    @POST
    @Path("san-pham/danhsachSP")
    List<SanPhamCuaDonHangDTO> getAllSPCuaDH(Set<String> ids, @HeaderParam("Authorization") String authHeader);

    @PUT
    @Path("san-pham/updateSoLuong")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<SanPhamDTO> updateSL(@RequestBody List<UpdateSLSPDTO> updates, @QueryParam("operation") Operation operation, @HeaderParam("Authorization") String authHeader);

}