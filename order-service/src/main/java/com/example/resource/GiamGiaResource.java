package com.example.resource;

import com.example.GiamGia;
import com.example.service.GiamGiaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/giamgia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GiamGiaResource {

    @Inject
    GiamGiaService giamGiaService;

    @POST
    public Response addGiamGia(GiamGia giamGia) {
        GiamGia created = giamGiaService.addGiamGia(giamGia);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response editGiamGia(@PathParam("id") Long id, GiamGia giamGia) {
        GiamGia updated = giamGiaService.editGiamGia(id, giamGia);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGiamGia(@PathParam("id") Long id) {
        if (giamGiaService.deleteGiamGia(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<GiamGia> listAll() {
        return giamGiaService.listAll();
    }
}

