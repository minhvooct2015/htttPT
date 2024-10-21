package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String maNguoiDung; // PK
    private String hoTen;        // Full Name
    private String email;        // Email
    private String sdt;          // Phone Number
    private String diaChi;       // Address
    private String matKhau;      // Password
    private String trangThai;     // Status
    private String taiKhoan;      // Account
    private String vaiTro;        // Role

}
