package com.example.resource;

import com.example.DTOS.SanPhamCuaDonHangDTO;
import com.example.DonHang;
import com.example.DonHangDTO;
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
    public Response addDonHang(DonHangDTO donHang) {
        donHang.setMaDh(null);
         donHangService.addDonHang(donHang);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response editDonHang(@PathParam("id") String id, DonHangDTO donHang) {
        donHangService.editDonHang(id, donHang);
        return Response.ok().build();
    }

    @PUT
    @Path("checkout/{id}")
    public Response editTragThaiDonHang(@PathParam("id") String id, DonHangDTO donHang) {
        donHangService.editTragThaiDonHang(id, donHang);
        return Response.ok().build();
    }


    @PUT
    @Path("hasDonHangDaDat/{manguoidung}")
    public Response hasDHDaDat(@PathParam("manguoidung") String manguoidung) {
        boolean b = donHangService.hasDonHangDangDat(manguoidung);
        return Response.ok().entity(b).build();
    }

    @GET
    @Path("/{id}")
    public DonHangDTO getDonHangById(@PathParam("id") String id) {
        return donHangService.getDonHangById(id);
    }

    @GET
    @Path("/by-user/{maNguoiDung}")
    public List<DonHangDTO> getDonHangByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung) {
        return donHangService.getDonHangByMaNguoiDung(maNguoiDung);
    }

    @GET
    @Path("/dssp/{maNguoiDung}")
    public List<SanPhamCuaDonHangDTO> getDsspByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung) {
        return donHangService.getDSSanPhamDonHangBy(maNguoiDung);
    }

    @GET
    @Path("/all-dssp")
    public List<SanPhamCuaDonHangDTO> getAllDssp() {
        return donHangService.getAllDSSanPhamDonHang();
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
    public List<DonHangDTO> listAll() {
        return donHangService.listAll();
    }
}

