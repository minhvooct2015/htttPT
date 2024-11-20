package com.example;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
public class MultipartBodyImageUpload {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    @FormParam("sanPhamDTO")
    @PartType(MediaType.TEXT_PLAIN)
    public String sanPhamDTO;
}
