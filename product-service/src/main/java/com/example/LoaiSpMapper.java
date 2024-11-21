package com.example;

public class LoaiSpMapper {

    //    @Mapping(source = "tenDanhMuc", target = "tenDanhMuc")
//    @Mapping(source = "moTa", target = "moTa")
    static LoaiSanPhamDTO toDTO(LoaiSanPham entity) {
        if (entity == null) {
            return null;
        }
        return new LoaiSanPhamDTO(
                entity.getMaDanhMuc(),
                entity.getTenDanhMuc(),
                entity.getMoTa()
        );
    }

    ;

    //    @Mapping(source = "tenDanhMuc", target = "tenDanhMuc")
//    @Mapping(source = "moTa", target = "moTa")
    public static LoaiSanPham toEntity(LoaiSanPhamDTO loaiSanPhamDTO) {
        if (loaiSanPhamDTO == null) {
            return null;
        }
        LoaiSanPham entity = new LoaiSanPham();
        entity.setTenDanhMuc(loaiSanPhamDTO.getTenDanhMuc());
        entity.setMoTa(loaiSanPhamDTO.getMoTa());
        return entity;
    }

}
