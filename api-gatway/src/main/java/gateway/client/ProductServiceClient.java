package gateway.client;

import gateway.client.model.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import java.util.List;
import java.util.Set;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.MultipartForm;

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


    @POST
    @Path("/san-pham")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Response addSanPham(
            @MultipartForm MultipartBodyImageUpload multipartBodyImageUpload,
            @QueryParam("loaiSanPhamId") String loaiSanPhamId
    );
}