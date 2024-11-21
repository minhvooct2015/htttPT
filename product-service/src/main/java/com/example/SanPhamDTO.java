package com.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
public class SanPhamDTO {


    private String maSP;
    private String tenSP;
    private String moTa;
    private Double giaSP;
    private Integer soLuongTonKho;

    private String hinhSP;
    private String loaiSP;

    private byte[] imageData;

    //consider => cho upload nhieu hinh anh (lam sau khi feature chinh done)
}



