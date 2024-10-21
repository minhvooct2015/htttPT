package com.example;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/san-pham")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SanPhamResource {

    @Inject
    SanPhamService sanPhamService;

    @GET
    public List<SanPham> getAllSanPham() {
        return sanPhamService.getAllSanPham();
    }

    @GET
    @Path("/{id}")
    public SanPham getSanPhamById(@PathParam("id") Long id) {
        return sanPhamService.getSanPhamById(id);
    }

    @POST
    @Transactional
    public Response addSanPham(SanPham sanPham, @QueryParam("loaiSanPhamId") Long loaiSanPhamId) {
        sanPhamService.addSanPham(sanPham, loaiSanPhamId);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSanPham(@PathParam("id") Long id, SanPham sanPham, @QueryParam("loaiSanPhamId") Long loaiSanPhamId) {
        sanPhamService.updateSanPham(id, sanPham, loaiSanPhamId);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteSanPham(@PathParam("id") Long id) {
        sanPhamService.deleteSanPham(id);
        return Response.noContent().build();
    }
}

