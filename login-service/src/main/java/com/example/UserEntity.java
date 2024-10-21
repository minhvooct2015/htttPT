package com.example;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "User")
@Getter
@Setter
public class UserEntity {

    @Id
    private String maNguoiDung; // PK
    private String hoTen;
    private String email;
    private String sdt;
    private String diaChi;
    private String matKhau;
    private String trangThai; // active/locked
    private String taiKhoan;
    private String vaiTro; // e.g., admin, customer


    public void setMatKhau(String password) {
        this.matKhau = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.matKhau);
    }
}
