package com.example.resource;

import com.example.GiaoHang;
import com.example.service.GiaoHangService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/giaohang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiaoHangResource {

    @Inject
    GiaoHangService giaoHangService;

    @POST
    public Response addGiaoHang(GiaoHang giaoHang) {
        GiaoHang created = giaoHangService.addGiaoHang(giaoHang);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response editGiaoHang(@PathParam("id") Long id, GiaoHang giaoHang) {
        GiaoHang updated = giaoHangService.editGiaoHang(id, giaoHang);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGiaoHang(@PathParam("id") Long id) {
        if (giaoHangService.deleteGiaoHang(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<GiaoHang> listAll() {
        return giaoHangService.listAll();
    }
}

