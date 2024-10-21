package com.example;


import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private TrangThai trangThai; // active/locked
    private String taiKhoan;
    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro;


    public void setMatKhau(String password) {
        this.matKhau = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.matKhau);
    }
}
