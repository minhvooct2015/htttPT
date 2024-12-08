package com.example.repo;

import com.example.ChiTietDonHang;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ChiTietDonHangRepository implements PanacheRepositoryBase<ChiTietDonHang, String> {
    // Custom methods if needed
    public ChiTietDonHang findByMaDHAndMaSP(String maDH, Long maSP) {
        return find("maDh = ?1 and maSp = ?2", maDH, maSP).firstResult();
    }

    public List<ChiTietDonHang> findByMaNguoiDung(String maNguoiDung) {
        return list("SELECT c FROM ChiTietDonHang c JOIN c.donHang d WHERE d.maNguoiDung = ?1", maNguoiDung);
    }

    /**
     * Deletes a ChiTietDonHang entity by its ID.
     *
     * @param id The ID of the entity to delete.
     * @return true if the entity was successfully deleted, false otherwise.
     */
    @Transactional
    public boolean deleteById(String id) {

        int rowsDeleted = getEntityManager()
                .createQuery("DELETE FROM ChiTietDonHang c WHERE c.maCtdh = :id")
                .setParameter("id", id)
                .executeUpdate();
        return rowsDeleted > 0;
    }

}
