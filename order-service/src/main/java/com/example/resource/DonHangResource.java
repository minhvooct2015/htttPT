package com.example.resource;

import com.example.DonHang;
import com.example.service.DonHangService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/donhang")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DonHangResource {

    @Inject
    DonHangService donHangService;

    @POST
    public Response addDonHang(DonHang donHang) {
        donHang.setMaDh(null);
        DonHang created = donHangService.addDonHang(donHang);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response editDonHang(@PathParam("id") String id, DonHang donHang) {
        DonHang updated = donHangService.editDonHang(id, donHang);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public DonHang getDonHangById(@PathParam("id") String id) {
        return donHangService.getDonHangById(id);
    }

    @GET
    @Path("/by-user/{maNguoiDung}")
    public List<DonHang> getDonHangByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung) {
        return donHangService.getDonHangByMaNguoiDung(maNguoiDung);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDonHang(@PathParam("id") String id) {
        if (donHangService.deleteDonHang(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<DonHang> listAll() {
        return donHangService.listAll();
    }
}

