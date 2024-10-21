package com.example;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/loai-san-pham")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoaiSanPhamResource {

    @Inject
    LoaiSanPhamService loaiSanPhamService;

    @GET
    public List<LoaiSanPham> getAllLoaiSanPham() {
        return loaiSanPhamService.getAllLoaiSanPham();
    }

    @GET
    @Path("/{id}")
    public LoaiSanPham getLoaiSanPhamById(@PathParam("id") Long id) {
        return loaiSanPhamService.getLoaiSanPhamById(id);
    }

    @POST
    @Transactional
    public Response addLoaiSanPham(LoaiSanPham loaiSanPham) {
        loaiSanPhamService.addLoaiSanPham(loaiSanPham);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateLoaiSanPham(@PathParam("id") Long id, LoaiSanPham loaiSanPham) {
        loaiSanPhamService.updateLoaiSanPham(id, loaiSanPham);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLoaiSanPham(@PathParam("id") Long id) {
        loaiSanPhamService.deleteLoaiSanPham(id);
        return Response.noContent().build();
    }
}

