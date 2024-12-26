package com.example;


import gateway.client.ProductServiceClient;
import gateway.client.model.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.MultipartForm;

import java.util.List;
import java.util.Set;

@Path("/san-pham")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    @RestClient
    ProductServiceClient sanPhamClient;

    @GET
    @Path("/all")
    public List<SanPhamDTO> getAllSanPham(@HeaderParam("Authorization") String authHeader) {
        return sanPhamClient.getAllSP("Bearer "+authHeader);
    }

    @GET
    @Path("/{id}")
    public SanPhamDTO getSanPhamById(@PathParam("id") String id, @HeaderParam("Authorization") String authHeader) {
        return sanPhamClient.getAll(id, authHeader);
    }

    @POST
    @Path("/danhsachSP")
    public  List<SanPhamCuaDonHangDTO>  getSanPhamById(Set<String> ids, @HeaderParam("Authorization") String authHeader) {
        return sanPhamClient.getAllSPCuaDH(ids, "Bearer "+authHeader);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addSanPham(
            @MultipartForm MultipartBodyImageUpload multipartBodyImageUpload,
            @QueryParam("loaiSanPhamId") String loaiSanPhamId
    ) {
        return sanPhamClient.addSanPham(multipartBodyImageUpload, loaiSanPhamId);
    }

}

