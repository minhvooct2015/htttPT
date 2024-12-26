package com.example;

import gateway.client.OrderClient;
import gateway.client.model.DonHangDTO;
import gateway.client.model.SanPhamCuaDonHangDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("")
public class OrderResource {

    @Inject
    @RestClient
    OrderClient orderClient;

    // Endpoint to create a new order
    @POST
    @Path("/donhang")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDonHang(DonHangDTO donHang, @HeaderParam("Authorization") String authHeader) {
        try {
            orderClient.addDonHang(donHang, authHeader);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while creating order: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint to checkout an order by ID
    @PUT
    @Path("/donhang/checkout/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkout(@PathParam("id") String id, DonHangDTO donHang, @HeaderParam("Authorization") String authHeader) {
        try {
            orderClient.checkout(id, donHang, authHeader);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error during checkout: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint to get all orders that are being processed
    @GET
    @Path("/donhang/all-dssp/dang-xu-ly")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDonHangDangXL(@HeaderParam("Authorization") String authHeader) {
        try {
            List<SanPhamCuaDonHangDTO> products = orderClient.getAllDonHangDangXL(authHeader);
            return Response.status(Response.Status.OK).entity(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving orders: " + e.getMessage())
                    .build();
        }
    }

    // Endpoint to get products by user ID
    @GET
    @Path("/donhang/dssp/{maNguoiDung}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDsspByMaNguoiDung(@PathParam("maNguoiDung") String maNguoiDung, @HeaderParam("Authorization") String authHeader) {
        try {
            List<SanPhamCuaDonHangDTO> products = orderClient.getDsspByMaNguoiDung(maNguoiDung, authHeader);
            return Response.status(Response.Status.OK).entity(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving products: " + e.getMessage())
                    .build();
        }
    }
}
