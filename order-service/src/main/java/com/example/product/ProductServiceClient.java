package com.example.product;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

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

}