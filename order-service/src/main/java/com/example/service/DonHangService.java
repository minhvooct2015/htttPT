package com.example.service;

import com.example.ChiTietDonHang;
import com.example.ChiTietDonHangDTO;
import com.example.DTOS.SanPhamCuaDonHangDTO;
import com.example.DTOS.UpdateSLSPDTO;
import com.example.DTOS.UpdateSLSPDTOKaf;
import com.example.DonHangDTO;
import com.example.enumss.Operation;
import com.example.enumss.TrangThaiDonHang;
import com.example.product.ProductServiceClient;
import com.example.repo.ChiTietDonHangRepository;
import com.example.repo.DonHangRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.DonHang;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DonHangService {

    @Inject
     DonHangRepository donHangRepository;

    @Inject
    ChiTietDonHangRepository chiTietDonHangRepository;

    @Inject
    @RestClient
    ProductServiceClient productServiceClient;

    @Inject
    OrderServiceKafka orderServiceKafka;

    @Transactional
    public boolean hasDonHangDangDat(String maNguoidung) {
        return !donHangRepository.findDHDaDatByMaNguoiDung(maNguoidung).isEmpty();
    }
    @Transactional
    public void addDonHang(DonHangDTO donHangDTO) {
        //consider call product to update

        //calculate gia tien luon
        List<DonHang> donHangList = donHangRepository.findDHDaDatByMaNguoiDung(donHangDTO.getMaNguoiDung());
        List<UpdateSLSPDTO> updateSLSPDTOS = new ArrayList<>();
        List<ChiTietDonHangDTO> dsCTDH = donHangDTO.getDsCTDH();

        if (donHangList.isEmpty()) {
            // Map DonHangDTO to DonHang entity
            DonHang donHang = OrderMapper.dtoToEntityDonHang(donHangDTO);
            donHang.setTrangThai(TrangThaiDonHang.DANG_DAT); // Set default status
            donHang.setMaDh(null);
            dsCTDH.stream().forEach(ctdh -> {
                updateSLSPDTOS.add(new UpdateSLSPDTO(ctdh.getMaSp(), ctdh.getSoLuong()));
            });
            // Persist the new DonHang
            donHangRepository.persist(donHang);

        } else {
            //add new ctsp
            DonHang donHangDangDat = donHangList.get(0);
            List<ChiTietDonHang> chiTietDonHangsEntity = donHangDangDat.getChiTietDonHangs();
            List<String> maSPInDB = chiTietDonHangsEntity.stream().map(ChiTietDonHang::getMaSp).collect(Collectors.toList());
            dsCTDH.stream()
                    .filter(ctdh -> !maSPInDB.contains(ctdh.getMaSp()))
                    .map(OrderMapper::dtoToEntityCTDH)
                    .peek(chiTietDonHang -> chiTietDonHang.setDonHang(donHangDangDat))
                    .forEach(chiTietDonHangRepository::persist);
             for(ChiTietDonHangDTO chiTietDonHangDTO: dsCTDH) {
                 String maSpInput = chiTietDonHangDTO.getMaSp();
                 updateSLSPDTOS.add(new UpdateSLSPDTO(chiTietDonHangDTO.getMaSp(), chiTietDonHangDTO.getSoLuong()));
                 if(maSPInDB.contains(maSpInput)) {
                     chiTietDonHangsEntity.stream().filter(ctdh -> ctdh.getMaSp().equals(maSpInput))
                             .forEach(ctsp -> ctsp.setSoLuong(ctsp.getSoLuong() + 1));
                 }
             }

            // Save and return the updated DonHang entity
            donHangRepository.persist(donHangDangDat);
        }
        orderServiceKafka.sendOrder(new UpdateSLSPDTOKaf(updateSLSPDTOS, Operation.SUB));
//        productServiceClient.updateSL(updateSLSPDTOS, Operation.SUB);
    }

    public List<DonHangDTO> getDonHangByMaNguoiDung(String maNguoiDung) {
        List<DonHang> donHangList = donHangRepository.findByMaNguoiDung(maNguoiDung);
        return donHangList.stream().map(OrderMapper::entityToDtoDonHang)
                .collect(Collectors.toList());
    }

    public List<SanPhamCuaDonHangDTO> getAllDSSanPhamDonHang() {
        List<ChiTietDonHang> allCTDH = chiTietDonHangRepository.findAllCTDH();
        return buildSanPhamCuaDonHangDTOS(allCTDH);
    }

    public List<SanPhamCuaDonHangDTO> getAllDSSanPhamDonHangXL() {
        List<ChiTietDonHang> allCTDH = chiTietDonHangRepository.findAllCTDHDaXL();
        return buildSanPhamCuaDonHangDTOS(allCTDH);
    }
    public List<SanPhamCuaDonHangDTO> getDSSanPhamDonHangBy(String maNguoiDung) {
        List<ChiTietDonHang> chiTietDonHangs = chiTietDonHangRepository.findByMaNguoiDung(maNguoiDung);
        List<SanPhamCuaDonHangDTO> result = buildSanPhamCuaDonHangDTOS(chiTietDonHangs);
        return result;
    }

    private List<SanPhamCuaDonHangDTO> buildSanPhamCuaDonHangDTOS(List<ChiTietDonHang> chiTietDonHangs) {
        List<ChiTietDonHangDTO> donHangDTOS = chiTietDonHangs.stream()
                .map(OrderMapper::entityToDtoCTDH)
                .collect(Collectors.toList());
        Set<String> dsSanPhamId = donHangDTOS.stream().map(ChiTietDonHangDTO::getMaSp).collect(Collectors.toSet());
        List<SanPhamCuaDonHangDTO> allSPCuaDH = productServiceClient.getAllSPCuaDH(dsSanPhamId);
        List<SanPhamCuaDonHangDTO> result = new ArrayList<>();
        if (chiTietDonHangs != null && allSPCuaDH != null) {

            // Iterate over allSPCuaDH and set each chiTietDonHangDTO from chiTietDonHangs
//            allSPCuaDH.forEach(sp -> {
//                // Assuming there's some logic to match them, e.g. by index or ID
//                donHangDTOS.stream()
//                        .filter(ctdh -> ctdh.getMaSp().equals(sp.getMaSP())) // Example match by ID
//                        .findFirst() // Assuming there's at least one matching item
//                        .ifPresent(ctdh -> {
//
//                            sp.setChiTietDonHangDTO(ctdh); // Set the corresponding ChiTietDonHangDTO
//                        });
//                sp.setTrangThaiDonHang(chiTietDonHangs.get(0).getDonHang().getTrangThai());
//                sp.setPhiGiaoHang(chiTietDonHangs.get(0).getDonHang().getPhiGiaoHang());
//            });
            chiTietDonHangs.forEach(ctdonhang -> {
                // Assuming there's some logic to match them, e.g. by index or ID
                allSPCuaDH.stream()
                        .filter(sp -> sp.getMaSP().equals(ctdonhang.getMaSp())) // Example match by ID
                        .forEach(sp -> {
                            SanPhamCuaDonHangDTO sanPhamCuaDonHangDTO = SanPhamCuaDonHangDTO.builder()
                                    .chiTietDonHangDTO(OrderMapper.entityToDtoCTDH(ctdonhang))
                                    .trangThaiDonHang(ctdonhang.getDonHang().getTrangThai())
                                    .phiGiaoHang(ctdonhang.getDonHang().getPhiGiaoHang())
                                    .ngayDat(ctdonhang.getDonHang().getNgayDatHang())
                                    .ngayThanhToan(ctdonhang.getDonHang().getNgayThanhToan())
                                    .hinhSP(sp.getHinhSP())
                                    .tongTien(ctdonhang.getDonHang().getTongTien())
                                    .soLuongTonKho(sp.getSoLuongTonKho())
                                    .giaSP(sp.getGiaSP())
                                    .maSP(sp.getMaSP())
                                    .tenSP(sp.getTenSP())
                                    .build();
                            result.add(sanPhamCuaDonHangDTO);
                        });

            });
        }
        return result;
    }

    public DonHangDTO getDonHangById(String id) {
        DonHang donhang = donHangRepository.findById(id);

        if (donhang == null) throw new NotFoundException("Khong co don hang " + id);
        DonHangDTO donHangDTO = OrderMapper.entityToDtoDonHang(donhang);
        return donHangDTO;
    }

    @Transactional
    public void editDonHang(String id, DonHangDTO donHangDTO) {
        // Find the existing DonHang entity by its ID (maDh)
        DonHang existingDonHang = donHangRepository.findById(id);

        // Update the fields of the existing entity with the values from DTO
        existingDonHang.setMaNguoiDung(donHangDTO.getMaNguoiDung());
        existingDonHang.setNgayDatHang(donHangDTO.getNgayDatHang());
        existingDonHang.setTongTien(donHangDTO.getTongTien());
        existingDonHang.setTrangThai(donHangDTO.getTrangThai());
        existingDonHang.setPhuongThucGiaoHang(donHangDTO.getPhuongThucGiaoHang());
        existingDonHang.setPhiGiaoHang(donHangDTO.getPhiGiaoHang());
        existingDonHang.setThoiGianDuKien(donHangDTO.getThoiGianDuKien());
        existingDonHang.setPhuongThucThanhToan(donHangDTO.getPhuongThucThanhToan());
        existingDonHang.setNgayThanhToan(donHangDTO.getNgayThanhToan());

        // Here you can handle the update of related entities such as ChiTietDonHang

        // Save and return the updated DonHang entity
         donHangRepository.persist(existingDonHang);
    }

    @Transactional
    public void editTragThaiDonHang(String id, DonHangDTO donHangDTO) {
        // Find the existing DonHang entity by its ID (maDh)
        DonHang existingDonHang = donHangRepository.findById(id);
        TrangThaiDonHang trangThai = donHangDTO.getTrangThai();
        if(trangThai.equals(TrangThaiDonHang.DANG_XU_LY)) {
            //todo tinh gia tien tai day
            existingDonHang.setNgayDatHang(LocalDate.now());
            existingDonHang.setTongTien(donHangDTO.getTongTien());
        }
        if(trangThai.equals(TrangThaiDonHang.DA_GIAO))
            existingDonHang.setNgayThanhToan(LocalDate.now());
        existingDonHang.setTrangThai(trangThai);

    }

    public boolean deleteDonHang(String id) {
        return donHangRepository.deleteById(id);
    }

    public List<DonHangDTO> listAll() {
        List<DonHang> donHangs = donHangRepository.listAll();
        return donHangs.stream().map(OrderMapper::entityToDtoDonHang)
                .collect(Collectors.toList());
    }
}

