package com.example.resource;

import com.example.ChiTietDonHang;
import com.example.service.ChiTietDonHangService;
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
    public Response addChiTietDonHang(ChiTietDonHang chiTietDonHang) {
        ChiTietDonHang created = chiTietDonHangService.addChiTietDonHang(chiTietDonHang);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response editChiTietDonHang(@PathParam("id") Long id, ChiTietDonHang chiTietDonHang) {
        ChiTietDonHang updated = chiTietDonHangService.editChiTietDonHang(id, chiTietDonHang);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteChiTietDonHang(@PathParam("id") Long id) {
        if (chiTietDonHangService.deleteChiTietDonHang(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<ChiTietDonHang> listAll() {
        return chiTietDonHangService.listAll();
    }
}

