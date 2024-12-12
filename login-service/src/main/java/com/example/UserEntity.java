package com.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

@Entity
@Table(name = "NguoiDung")
@Getter
@Setter
public class UserEntity {

    @Id
    private String maNguoiDung; // Formatted ID (will be set with random integer)

    private String hoTen;
    private String email;
    private String sdt;
    private String diaChi;
    private String matKhau;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai; // active/locked

    private String taiKhoan;

    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ma_PX", nullable = false)
    private PhuongXa phuongXa;


    /**
     * Set password as a hashed value.
     * The password will be hashed using BCrypt before storing.
     */
    public void setMatKhau(String password) {
        this.matKhau = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check if the provided password matches the stored hashed password.
     */
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.matKhau);
    }

    /**
     * Generate the formatted maNguoiDung (e.g., "U00" + random integer)
     * before persisting the entity.
     */
    @PrePersist
    public void prePersist() {
        if (maNguoiDung == null) {
            // Format the ID to match the format U00 + random integer
            this.maNguoiDung = "U00" + generateRandomId();
        }
    }

    /**
     * Method to generate a random integer as part of maNguoiDung.
     * The random integer will be between 100 and 999 (3 digits).
     */
    private String generateRandomId() {
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900); // Generates a random integer between 100 and 999
        return String.valueOf(randomNumber);
    }
}
