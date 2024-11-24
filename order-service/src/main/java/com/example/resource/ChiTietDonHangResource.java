package com.example.resource;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;
import com.example.service.ChiTietDonHangService;
import com.example.service.OrderMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/chitietdonhang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChiTietDonHangResource {

    @Inject
    ChiTietDonHangService chiTietDonHangService;

    @POST
    public Response addChiTietDonHang(ChiTietDonHangDTO chiTietDonHang) {
        ChiTietDonHangDTO created = chiTietDonHangService.addChiTietDonHang(chiTietDonHang);

        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response editChiTietDonHang(@PathParam("id") String id, @QueryParam("soLuong") Integer soLuong) {
         chiTietDonHangService.editChiTietDonHang(id, soLuong);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteChiTietDonHang(@PathParam("id") String id) {
        if (chiTietDonHangService.deleteChiTietDonHang(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<ChiTietDonHang> listAll() {
        return chiTietDonHangService.listAll();
    }

    @GET
    @Path("/{id}")
    public ChiTietDonHang getChiTietDonHangById(@PathParam("id") String id) {
        return chiTietDonHangService.getChiTietDonHangById(id);
    }

    @GET
    @Path("/don-hang/{maDH}")
    public List<ChiTietDonHang> getChiTietDonHangByMaDH(@PathParam("maDH") String maDH) {
        return chiTietDonHangService.getChiTietDonHangByMaDH(maDH);
    }
}

