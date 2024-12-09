package com.example.product;

import com.example.DTOS.SanPhamCuaDonHangDTO;
import com.example.DTOS.UpdateSLSPDTO;
import com.example.enumss.Operation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;
import java.util.Set;

@RegisterRestClient
public interface ProductServiceClient {

    @GET
    @Path("/loai-san-pham/all")
    List<LoaiSanPhamDTO> getAllLoaiSanPham();

    @GET
    @Path("/loai-san-pham/{id}")
    LoaiSanPhamDTO getLoaiSanPhamById(@PathParam("id") String id);


    @GET
    @Path("san-pham/all")
    List<SanPhamDTO> getAllSP();

    @GET
    @Path("san-pham/{id}")
    SanPhamDTO getAll(@PathParam("id") String id);


    @POST
    @Path("san-pham/danhsachSP")
    List<SanPhamCuaDonHangDTO> getAllSPCuaDH(Set<String> ids);

    @PUT
    @Path("san-pham/updateSoLuong")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<SanPhamDTO> updateSL(@RequestBody List<UpdateSLSPDTO> updates, @QueryParam("operation") Operation operation);

}