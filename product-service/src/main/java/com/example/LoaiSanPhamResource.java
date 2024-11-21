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
    @Path("/all")
    public List<LoaiSanPhamDTO> getAllLoaiSanPham() {
        return loaiSanPhamService.getAllLoaiSanPham();
    }

    @GET
    @Path("/{id}")
    public LoaiSanPham getLoaiSanPhamById(@PathParam("id") String id) {
        return loaiSanPhamService.getLoaiSanPhamById(id);
    }

    @POST
    @Transactional
    public Response addLoaiSanPham(LoaiSanPhamDTO loaiSanPham) {
        loaiSanPhamService.addLoaiSanPham(loaiSanPham);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Transactional
    @Path("addList")
    public Response addDsLoaiSanPham(List<LoaiSanPhamDTO> dsLoaiSanPham) {
        dsLoaiSanPham.forEach(loaiSanPhamService::addLoaiSanPham);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateLoaiSanPham(@PathParam("id") String id, LoaiSanPhamDTO loaiSanPham) {
        loaiSanPhamService.updateLoaiSanPham(id, loaiSanPham);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLoaiSanPham(@PathParam("id") String id) {
        loaiSanPhamService.deleteLoaiSanPham(id);
        return Response.noContent().build();
    }
}

