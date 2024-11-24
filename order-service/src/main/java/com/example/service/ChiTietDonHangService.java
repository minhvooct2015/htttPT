package com.example.service;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;
import com.example.DonHang;
import com.example.repo.ChiTietDonHangRepository;
import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ChiTietDonHangService {

    @Inject
    ChiTietDonHangRepository chiTietDonHangRepository;

    @Inject
    DonHangRepository donHangRepository;

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

    @Transactional
    public boolean deleteChiTietDonHang(String id) {

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
