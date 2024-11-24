package com.example.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    public List<LoaiSanPhamDTO> fetchAllLoaiSanPham() {
        return productServiceClient.getAllLoaiSanPham();
    }


}
