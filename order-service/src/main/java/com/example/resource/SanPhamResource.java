package com.example.resource;

import com.example.product.LoaiSanPhamDTO;
import com.example.product.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/product-service")
public class SanPhamResource {

    @Inject
    ProductService productService;

    @GET
    @Path("/loai-san-pham/all")
    public List<LoaiSanPhamDTO> getLoaiSanPham() {
        return productService.fetchAllLoaiSanPham();
    }
}
