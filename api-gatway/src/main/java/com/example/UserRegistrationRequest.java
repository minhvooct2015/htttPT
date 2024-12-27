package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String hoTen;        // Full Name
    private String email;        // Email
    private String sdt;          // Phone Number
    private String diaChi;       // Address
    private String matKhau;      // Password
    private TrangThai trangThai;     // Status
    private String taiKhoan;      // Account
    private VaiTro vaiTro;

}
