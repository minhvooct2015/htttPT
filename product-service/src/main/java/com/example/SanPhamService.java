package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class SanPhamService {

    @Inject
    SanPhamRepository sanPhamRepository;

    @Inject
    LoaiSanPhamRepository loaiSanPhamRepository;


    private static final Logger logger = LoggerFactory.getLogger(SanPhamService.class);
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";  // Change this to your desired directory


    public List<SanPhamDTO> getAllSanPham() {
        List<SanPham> sanPhams = sanPhamRepository.listAll();
        List<SanPhamDTO> sanPhamDTOS = sanPhams.stream()
                .map(SanphamMapper::toDTO).collect(Collectors.toList());
        return sanPhamDTOS;
    }

    public SanPhamDTO getSanPhamById(String id) {
        SanPham sanPham = sanPhamRepository.findByMaSP(id);

        return SanphamMapper.toDTO(sanPham);
    }

    @Transactional
    public void addSanPham(SanPhamDTO sanPham, String loaiSanPhamId, MultipartBodyImageUpload multipartBodyImageUpload) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findByIdLSP(loaiSanPhamId);
        logger.info("has loai sp id = {}", loaiSanPhamId);

        // Image upload logic (if applicable)

        if (loaiSanPham != null) {
            SanPham sanPhamE = SanphamMapper.toEntity(sanPham);
            sanPhamE.setMaSP("SP" + String.format("%03d", (int) (Math.random() * 1000)));
            sanPhamE.setLoaiSanPham(loaiSanPham);
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), sanPhamE.getMaSP());  // Assuming handleImageUpload is a method that handles the file upload
            sanPhamE.setHinhSP(imagePath);
            sanPhamE.setTenSP(sanPham.getTenSP());
            sanPhamRepository.persist(sanPhamE);
            logger.info("new sp added successfully");
        }
    }

    // Handle image upload and return the file path
    private String handleImageUpload(InputStream imageFileName, String maSp) {
        try {
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            String uniqueImageName = "Hinh-anh--" + maSp +".jpg";
            Path filePath = directoryPath.resolve(uniqueImageName);
            Files.copy(imageFileName, filePath);

            return filePath.toString();  // Return the path where the image is saved
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }

//    @Transactional
//    public void updateSanPham(String id, SanPham sanPham, String loaiSanPhamId) {
//        SanPham existing = sanPhamRepository.findByMaSP(id);
//        if (existing != null) {
//            LoaiSanPham loaiSanPham = loaiSanPhamRepository.findByIdLSP(loaiSanPhamId);
//            if (loaiSanPham != null) {
//                existing.setTenSP(sanPham.getTenSP());
//                existing.setMoTa(sanPham.getMoTa());
//                existing.setGiaSP(sanPham.getGiaSP());
//                existing.setSoLuongTonKho(sanPham.getSoLuongTonKho());
//                existing.setHinhSP(sanPham.getHinhSP());
//                existing.setLoaiSanPham(loaiSanPham);
//                sanPhamRepository.persist(existing);
//            }
//        }
//    }

    public boolean updateSanPham(
            String id,
            SanPhamDTO updatedSanPham,
            String loaiSanPhamId,
            MultipartBodyImageUpload multipartBodyImageUpload
    ) {
        SanPham existingSanPham = sanPhamRepository.findByMaSP(id);

        if (existingSanPham == null) {
            return false; // Product not found
        }

        // Update fields
        existingSanPham.setTenSP(updatedSanPham.getTenSP());
        existingSanPham.setMoTa(updatedSanPham.getMoTa());
        existingSanPham.setGiaSP(updatedSanPham.getGiaSP());
        existingSanPham.setSoLuongTonKho(updatedSanPham.getSoLuongTonKho());

        // Update category if provided
        if (loaiSanPhamId != null) {
            LoaiSanPham loaiSanPham = loaiSanPhamRepository.findByIdLSP(loaiSanPhamId);
            if(loaiSanPham != null) {
                existingSanPham.setLoaiSanPham(loaiSanPham);
            }
            else throw new IllegalArgumentException("Loai SP khong ton tai");

        }

        // Handle image upload if a new image is provided
        if (multipartBodyImageUpload.getFile() != null) {
            String imagePath = handleImageUpload(multipartBodyImageUpload.getFile(), existingSanPham.getMaSP());
            existingSanPham.setHinhSP(imagePath);
        }

        // Save the updated product
        sanPhamRepository.persist(existingSanPham);
        return true;
    }


    @Transactional
    public void deleteSanPham(String id) {
        sanPhamRepository.deleteByMaSP(id);
    }
}

