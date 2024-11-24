//package com.example.resource;
//
//import com.example.ThanhToan;
//import com.example.service.ThanhToanService;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/thanhtoan")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class ThanhToanResource {
//
//    @Inject
//    ThanhToanService thanhToanService;
//
//    @POST
//    public Response addThanhToan(ThanhToan thanhToan) {
//        ThanhToan created = thanhToanService.addThanhToan(thanhToan);
//        return Response.status(Response.Status.CREATED).entity(created).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response editThanhToan(@PathParam("id") Long id, ThanhToan thanhToan) {
//        ThanhToan updated = thanhToanService.editThanhToan(id, thanhToan);
//        if (updated != null) {
//            return Response.ok(updated).build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteThanhToan(@PathParam("id") Long id) {
//        if (thanhToanService.deleteThanhToan(id)) {
//            return Response.noContent().build();
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
//
//    @GET
//    public List<ThanhToan> listAll() {
//        return thanhToanService.listAll();
//    }
//}
