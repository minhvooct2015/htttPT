//package com.example.resource;
//
//import com.example.DanhGia;
//import com.example.service.DanhGiaService;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/danhgia")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class DanhGiaResource {
//
//    @Inject
//    DanhGiaService danhGiaService;
//
//    @POST
//    public Response addDanhGia(DanhGia danhGia) {
//        DanhGia created = danhGiaService.addDanhGia(danhGia);
//        return Response.status(Response.Status.CREATED).entity(created).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response editDanhGia(@PathParam("id") String id, DanhGia danhGia) {
//        DanhGia updated = danhGiaService.editDanhGia(id, danhGia);
//        if (updated != null) {
//            return Response.ok(updated).build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteDanhGia(@PathParam("id") String id) {
//        if (danhGiaService.deleteDanhGia(id)) {
//            return Response.noContent().build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//
//    @GET
//    public List<DanhGia> listAll() {
//        return danhGiaService.listAll();
//    }
//}
//
