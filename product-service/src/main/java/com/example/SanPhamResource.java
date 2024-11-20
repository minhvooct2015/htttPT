package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Path("/san-pham")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SanPhamResource {

    private static final String IMAGE_DIRECTORY = "src/main/resources/images";

    @Inject
    SanPhamService sanPhamService;

    @GET
    @Path("/all")
    public List<SanPhamDTO> getAllSanPham() {
        return sanPhamService.getAllSanPham();
    }

    @GET
    @Path("/{id}")
    public SanPhamDTO getSanPhamById(@PathParam("id") String id) {
        return sanPhamService.getSanPhamById(id);
    }


    @POST
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addSanPham(@MultipartForm MultipartBodyImageUpload multipartBodyImageUpload, @QueryParam("loaiSanPhamId") String loaiSanPhamId) {

        ObjectMapper mapper = new ObjectMapper();
        SanPhamDTO sanPham;
        try {
            sanPham = mapper.readValue(multipartBodyImageUpload.getSanPhamDTO(), SanPhamDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sanPhamService.addSanPham(sanPham, loaiSanPhamId, multipartBodyImageUpload);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("update/{id}")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateSanPham(
            @PathParam("id") String id,
            @MultipartForm MultipartBodyImageUpload multipartBodyImageUpload,
            @QueryParam("loaiSanPhamId") String loaiSanPhamId
    ) {
        ObjectMapper mapper = new ObjectMapper();
        SanPhamDTO updatedSanPham;
        try {
            // Parse the updated product data from the multipart request
            updatedSanPham = mapper.readValue(multipartBodyImageUpload.getSanPhamDTO(), SanPhamDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Call the service layer to update the product
        boolean updated = sanPhamService.updateSanPham(id, updatedSanPham, loaiSanPhamId, multipartBodyImageUpload);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }

        return Response.ok().entity("Product updated successfully").build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteSanPham(@PathParam("id") String id) {
        sanPhamService.deleteSanPham(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{imageName}")
    @Produces({"image/jpeg", "image/png"})
    public Response getImage(@jakarta.ws.rs.PathParam("imageName") String imageName) {
        try {
            java.nio.file.Path imagePath = Paths.get(IMAGE_DIRECTORY, imageName);
            if (!Files.exists(imagePath)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(Files.newInputStream(imagePath)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

