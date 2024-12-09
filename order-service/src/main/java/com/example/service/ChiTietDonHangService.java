package com.example.service;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;
import com.example.DTOS.UpdateSLSPDTO;
import com.example.DonHang;
import com.example.enumss.Operation;
import com.example.product.ProductServiceClient;
import com.example.repo.ChiTietDonHangRepository;
import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChiTietDonHangService {

    @Inject
    ChiTietDonHangRepository chiTietDonHangRepository;

    @Inject
    DonHangRepository donHangRepository;

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    @Transactional
    public ChiTietDonHangDTO addChiTietDonHang(ChiTietDonHangDTO chiTietDonHang) {
        chiTietDonHang.setMaCtdh(null);
        ChiTietDonHang chiTietDonHangE = OrderMapper.dtoToEntityCTDH(chiTietDonHang);
        DonHang donhang = donHangRepository.findById(chiTietDonHang.getMaDh());
        if(donhang !=null) chiTietDonHangE.setDonHang(donhang);
        else throw  new NotFoundException(" Khong tim that don hang");
        chiTietDonHangRepository.persist(chiTietDonHangE);
        ChiTietDonHangDTO chiTietDonHangDTO = OrderMapper.entityToDtoCTDH(chiTietDonHangE);
        chiTietDonHangDTO.setMaDh(donhang.getMaDh());
        return chiTietDonHangDTO;
    }

    //validate so luong khong lon hon so luong sanpham
    @Transactional
    public void editChiTietDonHang(String id, Integer soLuong) {
        ChiTietDonHang chiTiet = chiTietDonHangRepository.findById(id);
        if (chiTiet != null) {
            chiTiet.setSoLuong(soLuong);
            chiTietDonHangRepository.persist(chiTiet);
        }
    }


    public boolean deleteChiTietDonHang(String id) {
        ChiTietDonHang ctdh = chiTietDonHangRepository.findById(id);
        if (ctdh == null) {
            return false;
        }
        productServiceClient.updateSL(List.of(new UpdateSLSPDTO(ctdh.getMaSp(), ctdh.getSoLuong())), Operation.PLUS);

        return chiTietDonHangRepository.deleteById(id);
    }

    public ChiTietDonHang getChiTietDonHangById(String id) {
        return chiTietDonHangRepository.findById(id);
    }

    public List<ChiTietDonHang> getChiTietDonHangByMaDH(String maDH) {
        return chiTietDonHangRepository.list("maDh", maDH);
    }

    public List<ChiTietDonHang> listAll() {
        return chiTietDonHangRepository.listAll();
    }
}
